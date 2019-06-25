package de.internship.server.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.internship.server.helper.Utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Message {

    // declaration

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long msgId;

    private long sendTime;

    private String msgContent;
    private String transmitterUsername;
    private String receiverUsername;

    // utils

    public Message() {
    }

    // getters and setters

    public Message(Long msgId, String msgContent, String transmitterUsername, String receiverUsername) {
        this.msgId = msgId;
        this.msgContent = msgContent;
        this.transmitterUsername = transmitterUsername;
        this.receiverUsername = receiverUsername;
        this.sendTime = Utils.getTimeInMs();
    }

    public Message(String msgContent, String transmitterUsername, String receiverUsername, long sendTime) {
        this.msgContent = msgContent;
        this.transmitterUsername = transmitterUsername;
        this.receiverUsername = receiverUsername;
        this.sendTime =  Utils.getTimeInMs();
    }

    public Message(Long msgId, String msgContent, String transmitterUsername, String receiverUsername, long sendTime) {
        this.msgId = msgId;
        this.msgContent = msgContent;
        this.transmitterUsername = transmitterUsername;
        this.receiverUsername = receiverUsername;
        this.sendTime = sendTime;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setContent(String content) {
        this.msgContent = content;
    }

    public String getTransmitterUsername() {
        return transmitterUsername;
    }

    public void setTransmitterUsername(String transmitterUsername) {
        this.transmitterUsername = transmitterUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
