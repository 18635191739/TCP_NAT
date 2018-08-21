package com.verCpp;

import com.basic.ErrorHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerThread extends Thread {

    public static boolean IsStartPenetration = false;
    private String send;
    public boolean isFirst;//标记首个入站client
    public static boolean isKeepAlive = true; //需要关闭一个长连接时将其设置为false
    private Socket connection;


    ServerThread(Socket connection, boolean isFirst) {
        this.connection = connection;
        this.isFirst = isFirst;
    }

    public void StartPenetration(String ip1, String port1, String ip2, String port2) {
        send = isFirst ? "add_" + ip1 + "_" + port1 : "add_" + ip2 + "_" + port2;
        ServerThread.IsStartPenetration = true;
    }

    @Override
    public void run() {
        try {
            //I/O settings
            String codeFormat = "UTF-8";
            OutputStream outputStream = connection.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream, codeFormat);
            writer = new BufferedWriter(writer);
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, codeFormat));

            InetAddress address = connection.getInetAddress();
            System.out.println("新连接，客户端的IP：" + address.getHostAddress() + " ,端口：" + connection.getPort());
            //如果是第一个入站链接，则保持一个长连接

            if (isFirst) {
                try {
                    //等待客户端先“发言“
                    String clientMsg;
                    while ((clientMsg = reader.readLine()) != null) {  //读取完毕则跳出
                        // 循环读取客户端的信息
                        System.out.println("客户端1：" + clientMsg);
                        // 获取输出流，响应客户端的请求
                        writer.write("FirstConnection_欢迎“客户端1”连接服务器\n");
                        // 调用flush()方法将缓冲输出
                        writer.flush();
                    }
                    System.out.println("结束读取客户端信息");

                } catch (IOException ex) {
                    ErrorHandler.DoWhenError(ex, "读取/写入客户端出错！");
                } finally {
                    System.out.println("客户端关闭：" + address.getHostAddress() + " ,端口：" + connection.getPort());
                    // 关闭资源
                    try {
                        //关闭writer与reader
//                        if (writer != null) {
//                            writer.close();
//                        }
//                        if (reader != null) {
//                            reader.close();
//                        }
                        //关闭连接
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (IOException ex) {
                        ErrorHandler.DoWhenError(ex, "关闭客户端1连接时出错");
                    }
                }
            }
        } catch (IOException ex) {
            ErrorHandler.DoWhenError(ex, "与客户端建立链接失败，客户端IOStream不可获取");
        }
    }
}