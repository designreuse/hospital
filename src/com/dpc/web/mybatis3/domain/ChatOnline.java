package com.dpc.web.mybatis3.domain;

import java.util.Date;

public class ChatOnline {
    private Integer id;

    private String timePoint;

    private Integer fromUserId;

    private Integer toUserID;

    private String fromChat;

    private String toChat;

    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserID() {
        return toUserID;
    }

    public void setToUserID(Integer toUserID) {
        this.toUserID = toUserID;
    }

    public String getFromChat() {
        return fromChat;
    }

    public void setFromChat(String fromChat) {
        this.fromChat = fromChat == null ? null : fromChat.trim();
    }

    public String getToChat() {
        return toChat;
    }

    public void setToChat(String toChat) {
        this.toChat = toChat == null ? null : toChat.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }
}