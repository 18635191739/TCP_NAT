package com.NewServer;

import com.myServer.Server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.Socket;
import java.util.Date;


public class SKServer {
    public static void StartServer() {
        try {
            //创建serversocket
            ServerSocket ss = new ServerSocket(8888, 10);
            //设置socket选项
            // ss.setReuseAddress(true); //System.out.println("Reusable:"+ss.getReuseAddress()); //服务器默认可重用 无需设置

            // 记录客户端的数量
            int count = 0;
            System.out.println("***服务器启动，等待客户端的连接***");
            while (true) {
                try {
                    Socket connection = ss.accept();
                    System.out.println("收到客户端链接请求，启动服务线程");
                    Thread task = new SKServerThread(connection);
                    task.start();
                    count++;
                } catch (Exception ex) {
                    System.out.println("无法启动与客户端链接");
                    System.out.println("Errorlog:" + ex.toString());
                    System.out.println("StackTrace:");
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.println("无法启动服务器");
            System.out.println("Errorlog:" + ex.toString());
            System.out.println("StackTrace:");
            ex.printStackTrace();

        }
    }

    private static class SKServerThread extends Thread {
        private Socket connection;

        SKServerThread(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                out.write("Hello,This is SKServer"+ new Date().toString());
                out.flush();
                System.out.println("向客户端发送消息"+new Date().toString());
            }catch (IOException ex){
                System.out.println("无法写入客户端");
                System.out.println("Errorlog:" + ex.toString());
                System.out.println("StackTrace:");
                ex.printStackTrace();
            }finally {
                try{
                    System.out.println("关闭链接");
                    connection.close(); //不关闭链接客户端的getInputStream无法解析
                }catch (IOException ex){

                }
            }
        }
    }
}

