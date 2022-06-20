package com.remotedesktop.netty.handler;

import com.remotedesktop.netty.message.PCEnrollRequestMessage;
import com.remotedesktop.netty.message.PCEnrollResponseMessage;
import com.remotedesktop.netty.protocol.Command;
import com.remotedesktop.session.PCSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PCEnrollRequestMessageHandler extends SimpleChannelInboundHandler<PCEnrollRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PCEnrollRequestMessage pcEnrollRequestMessage) throws Exception {
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

        if (pcEnrollRequestMessage.getMessageType() != Command.PCENROLLREQUESTMESSAGE) {
            throw new RuntimeException("PCENROLLREQUESTMESSAGE");
        }
        String identificationCode = pcEnrollRequestMessage.getIdentificationCode();
        PCSession.registered(identificationCode,channelHandlerContext.channel());
        PCEnrollResponseMessage pcEnrollResponseMessage=new PCEnrollResponseMessage();
        pcEnrollResponseMessage.setRegistered(true);
        channelHandlerContext.channel().writeAndFlush(pcEnrollResponseMessage);
    }
}
