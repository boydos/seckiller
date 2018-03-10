package com.seckiller.service;

import com.seckiller.dto.Execution;
import com.seckiller.dto.Exposer;
import com.seckiller.entity.Seckiller;
import com.seckiller.exception.RepeatException;
import com.seckiller.exception.SeckillCloseException;
import com.seckiller.exception.SeckillException;

import java.util.Date;
import java.util.List;

/**
 * 秒杀服务接口，站在使用者角度
 */
public interface SeckillService {
    /**
     * 查询所有秒杀活动
     * @return
     */
    List<Seckiller> queryAll() ;

    /**
     * 根据ID查询一个秒杀单
     * @param seckillerId
     * @return
     */
    Seckiller queryById(long seckillerId);

    /**
     * 暴露秒杀接口，否则返回秒杀活动时间
     * @param seckillerId
     * @param now
     */
    Exposer exposerSeckillUrl(long seckillerId, Date now);

    /**
     * 执行秒杀，返回秒杀信息
     * @param seckillerId
     * @param userPhone
     * @param md5
     * @return
     */
    Execution executeSeckill(long seckillerId, long userPhone, String md5)
    throws SeckillException,RepeatException,SeckillCloseException;

}
