package com.seckiller.exception;

import com.seckiller.enums.SeckillEnums;

public class SeckillException extends RuntimeException {

    public SeckillException(SeckillEnums seckillEnums) {
        super(seckillEnums.getStateInfo());
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
