package com.seckiller.dto;

import com.seckiller.entity.SuccessKilled;
import com.seckiller.enums.SeckillEnums;

/**
 * 返回秒杀执行结果
 */
public class Execution {

    private long seckillerId;

    private int state;

    private String stateInfo;

    private long now;

    private SuccessKilled successKilled;

    public Execution(long seckillerId,SeckillEnums seckillEnums, long now, SuccessKilled successKilled) {
        this.seckillerId = seckillerId;
        this.state = seckillEnums.getState();
        this.stateInfo = seckillEnums.getStateInfo();
        this.now = now;
        this.successKilled = successKilled;
    }

    public Execution(long seckillerId, SeckillEnums seckillEnums) {
        this.seckillerId = seckillerId;
        this.state = seckillEnums.getState();
        this.stateInfo = seckillEnums.getStateInfo();
    }

    @Override
    public String toString() {
        return "{" +
                "seckillerId:" + seckillerId +
                ", state:" + state +
                ", stateInfo:'" + stateInfo + '\'' +
                ", now:" + now +
                ", successKilled:" + successKilled +
                '}';
    }
}
