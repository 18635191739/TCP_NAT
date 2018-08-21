package com.verCpp;

import com.basic.ErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class cClient {

    //首次与外网主服务器通信的本地端口
    public static int myPort;

    //TODO在此输入外网地址和端口
    public static String ip = "103.46.128.43";
    public static int port = 25508;

    public static void StartClient() {
        try {

            //新建socket暂不声明以供设置
            Socket socket = new Socket();
            // 设置reuseAddress为true
            socket.setReuseAddress(true);
            //连接外网主服务器
            socket.connect(new InetSocketAddress(ip, port));
            //首次与外网服务器通信的端口
            myPort = socket.getLocalPort();   //就意味着我们内网服务要与其他内网主机通信，就可以利用这个通道
            System.out.println("myPort:" + myPort);
            //I/O settings
            String codeFormat = "UTF-8";
            OutputStream outputStream = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream, codeFormat);
            writer = new BufferedWriter(writer);
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, codeFormat));
            //尝试写入服务器
            try {
                writer.write("Hello,我是一个快乐的客户端");
                writer.flush();
            } catch (IOException ex) {
                ErrorHandler.DoWhenError(ex, "写入服务器" + ip + ":" + port + "失败");
            }
            //循环读取服务器
            String clientMsg;
            while ((clientMsg = reader.readLine()) != null) {  //读取完毕则跳出
                System.out.println("主服务器：" + clientMsg);
            }
        } catch (IOException ex) {
            ErrorHandler.DoWhenError(ex, "与服务器" + ip + ":" + port + "连接失败");
        }

    }
}
