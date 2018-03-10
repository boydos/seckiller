package com.seckiller.service;

import com.seckiller.dto.Exposer;
import com.seckiller.dto.SeckillExecution;
import com.seckiller.entity.Seckiller;
import com.seckiller.exception.SeckillCloseException;
import com.seckiller.exception.SeckillRepeatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SeckillService seckillService;


    @Test
    public void getSeckillerList() {
        List<Seckiller> seckillers = seckillService.getSeckillerList();
        logger.info("seckillers={}",seckillers);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckiller seckiller = seckillService.getById(id);
        logger.info("seckiller={}",seckiller);
    }

    @Test
    public void exposeSeckill() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);

        if( exposer.isExpose() ) {
            long userPhone = 15022559989L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id,userPhone,md5);
                logger.info("result={}",execution);
            } catch (SeckillRepeatException e) {
                logger.warn(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.warn(e.getMessage());
            }
        }

    }

}