package com.remotedesktop.netty.message;

import java.io.Serializable;

public class Message implements Serializable {
    private int messageType;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
