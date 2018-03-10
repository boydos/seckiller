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
