package de.internship.server.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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

    public Message(Long ID, String content, String transmitterUsername, String receiverUsername) {
        this.msgId = ID;
        this.msgContent = content;
        this.transmitterUsername = transmitterUsername;
        this.receiverUsername = receiverUsername;
        this.sendTime = getTimeInMs();
    }

    public Message(Long ID, String content, String transmitterUsername, String receiverUsername, long sendTime) {
        this.msgId = ID;
        this.msgContent = content;
        this.transmitterUsername = transmitterUsername;
        this.receiverUsername = receiverUsername;
        this.sendTime = sendTime;
    }

    public long getTimeInMs() {
        Date date = new Date();
        return date.getTime();
    }

    public Long getMsgID() {
        return msgId;
    }

    public void setMsgID(Long ID) {
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
