package com.verCpp;
import com.basic.ErrorHandler;
import com.myServer.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

public class cServer {
    public String firstIP;
    public String firstPort;
    private String secondIP;
    String secondPort;

    public static void StartServer(){
        try {
            // 1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            // 记录客户端的数量
            int count = 0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            // 循环监听等待客户端的连接
            while (true) {
                // 调用accept()方法开始监听，等待客户端的连接
                socket = serverSocket.accept();
                // 创建一个新的线程
                Thread task = count==0? new ServerThread(socket,true):new ServerThread(socket,false);
                // 启动线程
                task.start();
                count++;// 统计客户端的数量
                System.out.println("客户端的数量：" + count);
                //当客户端为2时进行打洞
            }

        } catch (IOException e) {
            ErrorHandler.DoWhenError(e,"无法启动服务器");
        }
    }

}

