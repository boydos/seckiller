package com.seckiller.exception;

import com.seckiller.enums.SeckillEnums;

public class RepeatException extends SeckillException {

    public RepeatException(SeckillEnums seckillEnums) {
        super(seckillEnums);
    }

    public RepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
