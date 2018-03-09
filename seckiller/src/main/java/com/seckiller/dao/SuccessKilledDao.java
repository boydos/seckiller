package com.seckiller.dao;

import com.seckiller.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillerId
     * @param userPhone
     * @return 影响行数 >1 成功，0 失败
     */
    int insertSuccessKilled(@Param("seckillerId") long seckillerId,@Param("userPhone") long userPhone);

    /**
     * 根据ID和电话,查询SuccessKilled秒杀对象，并携带秒杀实体信息
     * @param seckillerId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillerId") long seckillerId,@Param("userPhone") long userPhone);
}
