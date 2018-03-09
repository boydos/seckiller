package com.seckiller.dao;

import com.seckiller.entity.Seckiller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    SeckillDao seckillDao;

    @Test
    public void queryById() {
        long id = 1000;
        Seckiller seckiller = seckillDao.queryById(id);

        System.out.println(seckiller.getName());
        System.out.println(seckiller);
    }

    @Test
    public void queryAll() {
        List<Seckiller> seckillers = seckillDao.queryAll(0,100);

        for(Seckiller sk: seckillers) {
            System.out.println(sk);
        }
    }

    @Test
    public void reduceNumber() {
        long id = 1000;
        Date skillTime = new Date();

        int ret = seckillDao.reduceNumber(id,skillTime);

        System.out.println("update count ="+ret);

    }




}