package com.seckiller.exception;

import com.seckiller.enums.SeckillEnums;

public class SeckillRepeatException extends SeckillException {

    public SeckillRepeatException(SeckillEnums seckillEnums) {
        super(seckillEnums);
    }

    public SeckillRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
