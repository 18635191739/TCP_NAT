package com.NewClient;

import com.basic.ErrorHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class SKClient {
    public static void StartClient(){
        try{
            String hostname = "215q64265h.iask.in";
            int port = 25508;
            hostname="localhost";
            port=8888;
            System.out.println("开始链接服务器");
            Socket sk = new Socket();
            //设置socket
            sk.setReuseAddress(true);
            sk.setSoTimeout(15000);
            sk.connect(new InetSocketAddress(hostname,port));
            InputStream in = sk.getInputStream();
            System.out.println("等待服务器信息");
            StringBuilder stringBuilder = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in);
            for (int c = reader.read(); c!=-1;c=reader.read()){
                stringBuilder.append((char)c);
            }
            System.out.println(stringBuilder);
        }catch (Exception ex){
            ErrorHandler.DoWhenError(ex,"无法链接到服务器");
        }

    }
}
