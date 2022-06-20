package com.remotedesktop.netty.message;

import com.remotedesktop.netty.protocol.Command;
import com.remotedesktop.netty.message.Message;

public class PCEnrollResponseMessage extends Message {
    private boolean isRegistered=false;
    public PCEnrollResponseMessage() {
        super.setMessageType(Command.PCENROLLRESPONSEMESSAGE);
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
