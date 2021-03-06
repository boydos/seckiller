package com.seckiller.enums;

public enum  SeckillEnums {
    SUCCESS(0,"秒杀成功"),
    REPEAT_KILL(1,"重复秒杀"),
    END(2,"秒杀结束"),
    DATA_REWRITE(3,"数据被篡改"),
    INNER_ERROR(4,"系统错误")
    ;
    private int state;

    private String stateInfo;

    SeckillEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
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

    public static SeckillEnums stateOf(int index) {
        for(SeckillEnums seckillEnums : values()) {
            if(seckillEnums.getState() == index) {
                return seckillEnums;
            }
        }
        return null;
    }
}
