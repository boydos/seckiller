package com.seckiller.service;

import com.seckiller.dto.Execution;
import com.seckiller.dto.Exposer;
import com.seckiller.entity.Seckiller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    @Autowired
    SeckillService seckillService;

    @Test
    public void queryAll() {
        List<Seckiller> seckillers = seckillService.queryAll();
        for(Seckiller seckiller : seckillers) {
            System.out.println(seckiller);
        }
    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckiller seckiller = seckillService.queryById(id);
        System.out.println(seckiller);
    }

    @Test
    public void exposerSeckillUrl() {
        long id = 1000;
        Date now = new Date(System.currentTimeMillis()+24*60*60*1000);
        Exposer exposer = seckillService.exposerSeckillUrl(id,now);
        System.out.println(exposer);
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long userPhone = 15011550099L;
        Date now = new Date();
        Exposer exposer = seckillService.exposerSeckillUrl(id,now);
        if( exposer.isEnable() ) {
           Execution execution = seckillService.executeSeckill(id,userPhone,exposer.getMd5());
           System.out.println(execution);
        }
    }


}