package com.seckiller.service.impl;

import com.seckiller.dao.SeckillDao;
import com.seckiller.dao.SuccessKilledDao;
import com.seckiller.dto.SeckillExecution;
import com.seckiller.dto.Exposer;
import com.seckiller.entity.Seckiller;
import com.seckiller.entity.SuccessKilled;
import com.seckiller.enums.SeckillEnums;
import com.seckiller.exception.SeckillRepeatException;
import com.seckiller.exception.SeckillCloseException;
import com.seckiller.exception.SeckillException;
import com.seckiller.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    private final String SLAT = "qefdasdfae!$$%^&**(())~_+~";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SeckillDao seckillDao;

    @Autowired
    SuccessKilledDao successKilledDao;

    public List<Seckiller> getSeckillerList() {

        return seckillDao.queryAll(0,100);
    }

    public Seckiller getById(long seckillerId) {

        return seckillDao.queryById(seckillerId);
    }


    public Exposer exportSeckillUrl(long seckillerId) {

        Seckiller seckiller = seckillDao.queryById(seckillerId);

        if( seckiller == null ) {
            //秒杀活动不存在
            return new Exposer(false,seckillerId);
        }

        Date start = seckiller.getStartTime();
        Date end = seckiller.getEndTime();
        Date now = new Date();
        if(now.getTime() < start.getTime()
                || now.getTime() >  end.getTime() ) {
            //秒杀尚未开始或者已经结束
            return new Exposer(false,seckillerId,now.getTime(),start.getTime(),end.getTime());
        }

        String md5 = getMd5(seckillerId);
        return new Exposer(true,seckillerId,md5);
    }

    private String getMd5(long seckillerId ) {

        String url = seckillerId + "/" + SLAT;
        return DigestUtils.md5DigestAsHex(url.getBytes());
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillerId, long userPhone, String md5)
            throws SeckillException, SeckillRepeatException, SeckillCloseException {

        if(md5 == null || !md5.equals(getMd5(seckillerId))) {
            //秒杀地址被篡改
            throw new SeckillException(SeckillEnums.DATA_REWRITE);
        }

        try {
            //记录购买明细
            int insertCount = successKilledDao.insertSuccessKilled(seckillerId,userPhone);
            if(insertCount <= 0) {
                //重复秒杀
                throw new SeckillRepeatException(SeckillEnums.REPEAT_KILL);
            } else {
                Date now = new Date();
                //减库存
                int updateCount = seckillDao.reduceNumber(seckillerId,now);

                if(updateCount<=0) {
                    //秒杀失败，库存为0或者已经结束--秒杀结束
                    throw new SeckillCloseException(SeckillEnums.END);
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillerId,userPhone);
                    return new SeckillExecution(seckillerId,SeckillEnums.SUCCESS,successKilled);
                }
            }
        } catch (SeckillRepeatException e) {
            throw e;
        } catch (SeckillCloseException e) {
            throw e;
        } catch (SeckillException e) {
            logger.info("Error = {}",e.getMessage());
            throw new SeckillException(SeckillEnums.INNER_ERROR);
        }
    }
}
