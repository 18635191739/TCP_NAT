package com.NewClient;

import com.basic.ErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

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
    public static void SearchServerPort(String hostname,int start,int end){
        long startTime=System.currentTimeMillis();//记录开始时间
        for(int i =start;i<=end;i++){
            try{
                Socket s = new Socket();
                System.out.println("check port"+hostname+":"+i);
                s.connect(new InetSocketAddress(hostname,i),150);
                System.out.println("There is a server at "+hostname+":"+i);
                s.close();
            }catch (UnknownHostException ex){
                //ErrorHandler.DoWhenError(ex,"扫描服务器端口出错");
                System.out.println("扫描服务器端口出错");
            }
            catch (IOException ex){
                //此端口非服务器
                //ErrorHandler.DoWhenError(ex,"非服务器端口");
                System.out.println("非服务器端口");
            }

        }
        long endTime=System.currentTimeMillis();//记录结束时间
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间："+excTime+"s");
    }
}
