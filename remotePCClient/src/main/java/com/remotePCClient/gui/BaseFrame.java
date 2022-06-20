package com.remotePCClient.gui;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;

import com.remotePCClient.entities.Result;
import com.remotePCClient.netty.message.PCEnrollRequestMessage;
import com.remotePCClient.netty.protocol.MyMessageCodec;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import javax.swing.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class BaseFrame {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        JLabel jLabel1=new JLabel("识别码:");
        JLabel jLabel2=new JLabel();
        InetAddress inetAddress= InetAddress.getLocalHost();

        String macAddress = NetUtil.getMacAddress(inetAddress);
        Map map=new HashMap();
        map.put("macAddress",macAddress);
        String response = HttpUtil.get("http://127.0.0.1:8081/pcrecord/getIdentificationCode",map);
        Gson gson=new Gson();
        Result result=gson.fromJson(response,Result.class);
        String data = (String) result.getData();

        new Bootstrap()
                .group(new NioEventLoopGroup())
                // 选择客户 Socket 实现类，NioSocketChannel 表示基于 NIO 的客户端实现
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new MyMessageCodec());
                        channel.pipeline().addLast(new SimpleChannelInboundHandler<PCEnrollRequestMessage>() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                PCEnrollRequestMessage pcEnrollRequestMessage=new PCEnrollRequestMessage();
                                pcEnrollRequestMessage.setIdentificationCode(data);
                                ctx.channel().writeAndFlush(pcEnrollRequestMessage);
                            }

                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, PCEnrollRequestMessage pcEnrollRequestMessage) throws Exception {

                            }
                        });

                    }
                })
                // 指定要连接的服务器和端口
                .connect(new InetSocketAddress("localhost", 8088))
                // Netty 中很多方法都是异步的，如 connect
                // 这时需要使用 sync 方法等待 connect 建立连接完毕
                .sync();
        jLabel2.setText(data);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jFrame.add(jPanel);
        jFrame.setBounds(200,2002,100,100);
        jFrame.setVisible(true);
    }
}
