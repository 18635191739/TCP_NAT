package com.MyServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class CommandSender {
    public static void SendCommandToServer(String comm) {
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new CommandSenderHandler());

            b.bind(7777).sync().channel().closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            group.shutdownGracefully();
        }
    }
}
