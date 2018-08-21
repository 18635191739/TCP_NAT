package com.ClientConnTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    //输入scanner
    private Scanner scanner = new Scanner(System.in);
    //是否等待输入
    private boolean isWaitInput = true;
    //首次与外网主机通信的连接
    private Socket socket;
    //首次与外网主机通信的本地端口
    private int localPort;

    private PrintWriter pw;
    private BufferedReader br;

    public static void StartClient() {
        new Client().start();
    }

    public void start() {
        try {
            // 新建一个socket通道
            Socket socket = new Socket();
            // 设置reuseAddress为true
            socket.setReuseAddress(true);

            //TODO在此输入外网地址和端口
            String ip = "103.46.128.43";
            int port = 25508;
            socket.connect(new InetSocketAddress(ip, port));

            //首次与外网服务器通信的端口
            //这就意味着我们内网服务要与其他内网主机通信，就可以利用这个通道
            localPort = socket.getLocalPort();

            System.out.println("本地端口：" + localPort);
            System.out.println("请输入命令 notwait等待穿透，或者输入conn进行穿透");

            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void StartC1(){

    }
}
