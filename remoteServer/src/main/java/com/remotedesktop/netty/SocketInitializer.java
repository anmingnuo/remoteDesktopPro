package com.remotedesktop.netty;

import com.remotedesktop.netty.handler.PCEnrollRequestMessageHandler;
import com.remotedesktop.netty.protocol.MyMessageCodec;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.stereotype.Component;

/**
 * Socket 初始化器，每一个Channel进来都会调用这里的 InitChannel 方法
 * @author Gjing
 **/
@Component
public class SocketInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(
                1024*1024*1024,10,4,0,0));
        pipeline.addLast(new MyMessageCodec());
        // 添加上自己的处理器
        pipeline.addLast(new PCEnrollRequestMessageHandler());
        pipeline.addLast(new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                System.out.println("wwwwwwwwwwwwwwwwwwwwwwww");
            }
        });

    }
}