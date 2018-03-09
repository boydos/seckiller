package com.seckiller.entity;

import java.util.Date;

public class Seckiller {

    private long seckillerId;

    private int number;

    private String name;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    public long getSeckillerId() {
        return seckillerId;
    }

    public void setSeckillerId(long seckillerId) {
        this.seckillerId = seckillerId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckiller{" +
                "seckillerId=" + seckillerId +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}