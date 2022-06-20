package com.remotePCClient.netty.message;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private int messageType;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
