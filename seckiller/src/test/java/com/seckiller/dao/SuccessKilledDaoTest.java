package com.seckiller.dao;

import com.seckiller.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long id = 1000;
        long phone = 13811006688L;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount ="+insertCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1000;
        long phone = 13811006688L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);

    }
}