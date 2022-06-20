package com.remotedesktop.session;

import io.netty.channel.Channel;

import java.util.HashMap;

public class PCSession {
    private static HashMap<String, Channel> pcClientsCodeAndChannel=new HashMap<>();
    private static HashMap<Channel,String> pcClientsChannelAndCode=new HashMap<>();
    public static void registered(String identificationCode,Channel channel){
        pcClientsCodeAndChannel.put(identificationCode,channel);
        pcClientsChannelAndCode.put(channel,identificationCode);

    }
    public static void inRegistered(String threadId){
        String identificationCode = pcClientsChannelAndCode.get(threadId);
        pcClientsCodeAndChannel.remove(identificationCode);
        pcClientsChannelAndCode.remove(threadId);
    }
    public static boolean isExist(String identificationCode){
        return pcClientsCodeAndChannel.containsKey(identificationCode);
    }
    public static Channel getThreadId(String identificationCode){
        return  pcClientsCodeAndChannel.get(identificationCode);
    }
}
