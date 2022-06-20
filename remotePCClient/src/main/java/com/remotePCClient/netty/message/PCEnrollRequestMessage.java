package com.remotePCClient.netty.message;


import com.remotePCClient.netty.protocol.Command;

public class PCEnrollRequestMessage extends Message {
    private String identificationCode;
    public PCEnrollRequestMessage() {
        super.setMessageType(Command.PCENROLLREQUESTMESSAGE);
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }
}