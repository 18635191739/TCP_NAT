package com.basic;

import com.NewClient.SKClient;
import com.NewServer.SKServer;
import com.myClient.Client;
import com.myServer.Server;
import java.net.InetAddress;
import java.net.*;
import java.io.*;
import  java.util.regex.*;

public class ProgramEntry {
    public static void main(String[] args){
        SKClient.SearchServerPort(getV4IP(),1,65535);

        for (String a: args
        ) {
            System.out.println("args:"+a);
        }
        if(args.length>0)
            switch (args[0]){
                case "S":
                    Server.StartServer();
                    break;
                case "C":
                    Client.StartClient();
                    break;
                case "sks":
                    SKServer.StartServer();
                    break;
                case "skc":
                    SKClient.StartClient();
                    break;
                case "scan":
                    SKClient.SearchServerPort("localhost",1,65537);
                default:
                    System.out.println("Error args");
                    break;
            }
            try{
                GetIP();
            }

        catch(Exception ex){

            }
            System.out.println("IP:"+getV4IP());
            try{

                ServerSocket ss = new ServerSocket(10240);
                System.out.println("Reusable:"+ss.getReuseAddress());
            }
            catch (Exception ex){

            }
    }

    public static void GetIP() throws Exception{
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println(ip);
        System.out.println(hostName);
    }
    public static String getV4IP(){
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            while((read=in.readLine())!=null){
                inputLine.append(read+"\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if(m.find()){
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }
}
