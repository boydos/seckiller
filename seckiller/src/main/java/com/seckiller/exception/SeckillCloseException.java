package com.seckiller.exception;

import com.seckiller.enums.SeckillEnums;

public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(SeckillEnums seckillEnums) {
        super(seckillEnums);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
