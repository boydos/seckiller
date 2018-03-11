package com.seckiller.dto;

import com.seckiller.entity.SuccessKilled;
import com.seckiller.enums.SeckillEnums;

/**
 * 返回秒杀执行结果
 */
public class SeckillExecution {

    private long seckillerId;

    private int state;

    private String stateInfo;

    private SuccessKilled successKilled;

    public SeckillExecution(long seckillerId, SeckillEnums seckillEnums,SuccessKilled successKilled) {
        this.seckillerId = seckillerId;
        this.state = seckillEnums.getState();
        this.stateInfo = seckillEnums.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillerId, SeckillEnums seckillEnums) {
        this.seckillerId = seckillerId;
        this.state = seckillEnums.getState();
        this.stateInfo = seckillEnums.getStateInfo();
    }

    public long getSeckillerId() {
        return seckillerId;
    }

    public void setSeckillerId(long seckillerId) {
        this.seckillerId = seckillerId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillerId=" + seckillerId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
