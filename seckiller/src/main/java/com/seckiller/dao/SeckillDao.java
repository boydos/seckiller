package com.seckiller.dao;

import com.seckiller.entity.Seckiller;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {

    /**
     * 减库存操作
     * @param seckillerId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillerId") long seckillerId,@Param("killTime") Date killTime);

    /**
     * 通过ID查询秒杀信息
     * @param seckillerId
     * @return
     */
    Seckiller queryById(long seckillerId);

    /**
     * 通过偏移量查询秒杀列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckiller>  queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
