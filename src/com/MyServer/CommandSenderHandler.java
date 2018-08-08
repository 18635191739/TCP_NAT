package com.MyServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;

public class CommandSenderHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
            throws Exception {
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端向服务器发送M");
        ctx.writeAndFlush(new DatagramPacket(
                Unpooled.copiedBuffer("M".getBytes()),
                new InetSocketAddress("127.0.0.1", 7402)));
        super.channelActive(ctx);
    }
}
