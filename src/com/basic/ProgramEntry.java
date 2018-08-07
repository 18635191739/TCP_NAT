package com.basic;

import com.MyClientL.EchoClient;
import com.MyClientR.EchoClient2;
import com.MyServer.EchoServer;
import java.util.Scanner;

public class ProgramEntry {
    public static void main(String[] args){
        for (String a: args
             ) {
            System.out.println("args:"+a);
        }
        if(args.length>0)
        switch (args[0]){
            case "S":
                EchoServer.StartServer();
                break;
            case "CL":
                EchoClient.StartClientL(new Scanner(System.in).next());
                break;
            case "CR":
                EchoClient2.StartClientR();
                break;
                default:
                    System.out.println("Error args");
                    break;
        }
    }
}
