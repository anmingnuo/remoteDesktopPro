package com.remotedesktop.netty.message;

import com.remotedesktop.netty.protocol.Command;



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
