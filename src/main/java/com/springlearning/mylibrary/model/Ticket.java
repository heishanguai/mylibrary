package com.springlearning.mylibrary.model;

import java.util.Date;

public class Ticket {

    private int id;

    /**
     * 像绑定的用户id
     */
    private int userId;

    /**
     * ticket实体
     */
    private String ticket;

    /**
     * 过期时间
     */
    private Date expiredAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
