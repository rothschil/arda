package io.github.rothschil.web.controller;

import io.github.rothschil.domain.jpa.entity.IntfLog;
import io.github.rothschil.domain.jpa.service.IntfLogJpaService;
import io.github.rothschil.domain.mybatis.entity.IntfEntity;
import io.github.rothschil.domain.mybatis.service.IntfService;
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
        log.info("UUID= {}", uuid);
        return active+":->"+uuid;
    }

    @Autowired
    private IntfService intfService;

    @Autowired
    private IntfLogJpaService intfLogJpaService;

    @RequestMapping("/intf")
    public IntfEntity intf(){
        return intfService.findByCaller("2");
    }

    @RequestMapping("/intf2")
    public IntfLog intf2(){
        return intfLogJpaService.getIntfLogByCaller("2");
    }


}
