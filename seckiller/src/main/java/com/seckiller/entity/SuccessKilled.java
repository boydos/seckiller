package com.seckiller.entity;

import java.util.Date;

public class SuccessKilled {

    private long seckillerId;

    private long userPhone;

    private  int state;

    private Date createTime;

    private Seckiller seckiller;

    public long getSeckillerId() {
        return seckillerId;
    }

    public void setSeckillerId(long seckillerId) {
        this.seckillerId = seckillerId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillerId=" + seckillerId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckiller=" + seckiller +
                '}';
    }
}
