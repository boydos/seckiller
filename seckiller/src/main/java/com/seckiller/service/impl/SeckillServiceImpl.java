package com.seckiller.service.impl;

import com.seckiller.dao.SeckillDao;
import com.seckiller.dao.SuccessKilledDao;
import com.seckiller.dto.Execution;
import com.seckiller.dto.Exposer;
import com.seckiller.entity.Seckiller;
import com.seckiller.entity.SuccessKilled;
import com.seckiller.enums.SeckillEnums;
import com.seckiller.exception.RepeatException;
import com.seckiller.exception.SeckillCloseException;
import com.seckiller.exception.SeckillException;
import com.seckiller.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    private final String SLAT = "qefdasdfae!$$%^&**(())~_+~";

    @Autowired
    SeckillDao seckillDao;

    @Autowired
    SuccessKilledDao successKilledDao;

    public List<Seckiller> queryAll() {

        return seckillDao.queryAll(0,100);
    }

    public Seckiller queryById(long seckillerId) {

        return seckillDao.queryById(seckillerId);
    }


    public Exposer exposerSeckillUrl(long seckillerId, Date now) {

        Seckiller seckiller = seckillDao.queryById(seckillerId);

        if( seckiller == null ) {
            return new Exposer(seckillerId,false);
        }

        Date start = seckiller.getStartTime();

        Date end = seckiller.getEndTime();

        if(now.getTime() < start.getTime()
                || now.getTime() >  end.getTime() ) {

            return new Exposer(seckillerId,false,now.getTime(),start.getTime(),end.getTime());
        }

        String md5 = getMd5(seckillerId);

        return new Exposer(seckillerId,true,md5);
    }

    private String getMd5(long seckillerId ) {

        String url = seckillerId + "/" + SLAT;

        return DigestUtils.md5DigestAsHex(url.getBytes());
    }

    @Transactional
    public Execution executeSeckill(long seckillerId, long userPhone, String md5)
            throws SeckillException, RepeatException, SeckillCloseException {

        if(md5 == null || !md5.equals(getMd5(seckillerId))) {
            throw new SeckillException(SeckillEnums.DATA_REWRITE);
        }

        try {
            int insertCount = successKilledDao.insertSuccessKilled(seckillerId,userPhone);
            if(insertCount <= 0) {
                throw new RepeatException(SeckillEnums.REPEAT);
            } else {

                Date now = new Date();

                int updateCount = seckillDao.reduceNumber(seckillerId,now);

                if(updateCount<=0) {
                    throw new SeckillCloseException(SeckillEnums.CLOSE);
                } else {

                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillerId,userPhone);

                    return new Execution(seckillerId,SeckillEnums.SUCCESS,now.getTime(),successKilled);
                }
            }
        } catch (RepeatException e) {
            throw e;
        } catch (SeckillCloseException e) {
            throw e;
        } catch (SeckillException e) {
            throw new SeckillException(SeckillEnums.INNER_ERROR);
        }
    }
}
