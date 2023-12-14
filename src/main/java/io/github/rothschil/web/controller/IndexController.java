package io.github.rothschil.web.controller;

import cn.hutool.json.JSONUtil;
import io.github.rothschil.domain.jpa.entity.IntfLogJpa;
import io.github.rothschil.domain.jpa.service.IntfLogJpaService;
import io.github.rothschil.domain.mybatis.entity.IntfLogBatis;
import io.github.rothschil.domain.mybatis.service.IntfBatisService;
import io.github.rothschil.web.compoent.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequestMapping("/iserv")
@RestController
public class IndexController {

    @Value("${github.active:test}")
    protected String active;

    @Autowired
    protected AsyncTask asyncTask;

    @RequestMapping("/index")
    public String index(){
        asyncTask.async();
        String uuid = UUID.randomUUID().toString();
        return active+":->"+uuid;
    }

    @Autowired
    private IntfBatisService intfBatisService;

    @Autowired
    private IntfLogJpaService intfLogJpaService;

    @RequestMapping("/intf")
    public IntfLogBatis intf(){
        IntfLogBatis intfLogJpa = intfBatisService.findByCaller("2");
        log.warn("IntfLogBatis {}", JSONUtil.parse(intfLogJpa));
        return intfLogJpa;
    }

    @RequestMapping("/intf2")
    public IntfLogJpa intf2(){
        IntfLogJpa intfLogJpa = intfLogJpaService.getIntfLogByCaller("2");
        log.warn("IntfLogJpa {}", JSONUtil.parse(intfLogJpa));
        return intfLogJpa;
    }


}
