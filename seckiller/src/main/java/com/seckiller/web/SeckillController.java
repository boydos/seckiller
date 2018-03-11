package com.seckiller.web;

import com.seckiller.dto.Exposer;
import com.seckiller.dto.SeckillExecution;
import com.seckiller.dto.SeckillerResult;
import com.seckiller.entity.Seckiller;
import com.seckiller.enums.SeckillEnums;
import com.seckiller.exception.SeckillCloseException;
import com.seckiller.exception.SeckillException;
import com.seckiller.exception.SeckillRepeatException;
import com.seckiller.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Seckiller> seckillers = seckillService.getSeckillerList();
        model.addAttribute("list",seckillers);
        return "list";
    }

    @GetMapping("/{seckillerId}/detail")
    public String detail(@PathVariable("seckillerId") Long seckillerId, Model model) {

        if(seckillerId == null) {
            return "redirect:/seckill/list";
        }
        Seckiller seckiller = seckillService.getById(seckillerId);

        if (seckiller == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckiller",seckiller);
        return "detail";
    }

    @PostMapping(value = "/{seckillerId}/exposer",
            produces = {"application/json;charset=UTF8"})
    @ResponseBody
    public SeckillerResult<Exposer> exposer(@PathVariable("seckillerId") Long seckillerId) {

        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillerId);
            return new SeckillerResult<Exposer>(true,exposer);
        }catch (Exception e) {
            logger.warn("error = {}",e.getMessage());
            return new SeckillerResult<Exposer>(false,"系统异常");
        }
    }

    @PostMapping(value = "/{seckillerId}/{md5}/execution",
            produces = {"application/json;charset=UTF8"})
    @ResponseBody
    public SeckillerResult<SeckillExecution> execution(@PathVariable("seckillerId") Long seckillerId,
                                                       @PathVariable("md5") String md5,
                                                       @CookieValue(value = "killPhone",required = false) Long phone) {

        if(phone == null) {
            return new SeckillerResult<SeckillExecution>(false,"未注册");
        }
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillerId,phone,md5);
            return new SeckillerResult<SeckillExecution>(true,execution);
        } catch (SeckillRepeatException e) {
            SeckillExecution execution = new SeckillExecution(seckillerId, SeckillEnums.REPEAT_KILL);
            return new SeckillerResult<SeckillExecution>(true,execution);
        } catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution(seckillerId, SeckillEnums.END);
            return new SeckillerResult<SeckillExecution>(true,execution);
        } catch (SeckillException e) {
            SeckillExecution execution = new SeckillExecution(seckillerId, SeckillEnums.INNER_ERROR);
            return new SeckillerResult<SeckillExecution>(true,execution);
        }
    }

    @GetMapping("/time/now")
    @ResponseBody
    public SeckillerResult<Long> time() {
        Date now = new Date();
        return new SeckillerResult<Long>(true,now.getTime());
    }


}
