package com.seckiller.service;

import com.seckiller.dto.Exposer;
import com.seckiller.dto.SeckillExecution;
import com.seckiller.entity.Seckiller;
import com.seckiller.exception.SeckillRepeatException;
import com.seckiller.exception.SeckillCloseException;
import com.seckiller.exception.SeckillException;

import java.util.List;

/**
 * 秒杀服务接口，站在使用者角度
 */
public interface SeckillService {
    /**
     * 查询所有秒杀活动
     * @return
     */
    List<Seckiller> getSeckillerList() ;

    /**
     * 根据ID查询一个秒杀单
     * @param seckillerId
     * @return
     */
    Seckiller getById(long seckillerId);

    /**
     * 暴露秒杀接口，否则返回秒杀活动时间
     * @param seckillerId
     */
    Exposer exportSeckillUrl(long seckillerId);

    /**
     * 执行秒杀，返回秒杀信息
     * @param seckillerId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillerId, long userPhone, String md5)
        throws SeckillException,SeckillRepeatException,SeckillCloseException;

}
