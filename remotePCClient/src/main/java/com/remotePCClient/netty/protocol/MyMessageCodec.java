package com.remotePCClient.netty.protocol;

import com.google.gson.Gson;
import com.remotePCClient.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import io.netty.handler.codec.MessageToMessageCodec;


import java.nio.charset.StandardCharsets;
import java.util.List;

public class MyMessageCodec extends MessageToMessageCodec<ByteBuf, Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        ByteBuf byteBuf=ctx.alloc().buffer();
        byteBuf.writeBytes("zygxyrjxy".getBytes(StandardCharsets.UTF_8));//魔术
        byteBuf.writeByte(1);
        byte[] bytes = new Gson().toJson(msg).getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(bytes.length);
        System.out.println("我是客户端编码器");
        byteBuf.writeBytes(bytes);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        byte[] magicNum=new byte[9];
        byteBuf.readBytes(magicNum);
        byteBuf.readByte();
        int length=byteBuf.readInt();
        byte[] bytes=new byte[length];
        System.out.println("------------------------------------");
        System.out.println(bytes.length);
        byteBuf.readBytes(bytes);
        String json = new String(bytes, StandardCharsets.UTF_8);
        Message message = new Gson().fromJson(json,Message.class);
        out.add(message);
    }
}