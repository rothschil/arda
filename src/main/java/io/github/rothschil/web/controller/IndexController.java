package io.github.rothschil.web.controller;

import cn.hutool.json.JSONUtil;
import io.github.rothschil.common.config.cache.CacheNames;
import io.github.rothschil.domain.jpa.entity.IntfLogJpa;
import io.github.rothschil.domain.jpa.service.IntfLogJpaService;
import io.github.rothschil.domain.mybatis.service.IntfBatisService;
import io.github.rothschil.web.compoent.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        asyncTask.async2();
        asyncTask.async3();

        String uuid = UUID.randomUUID().toString();
        log.warn("[Current Index]");
        return active+":->"+uuid;
    }


    /**
     * 测试 @Cacheable
     * <p>
     * 表示这个方法有了缓存的功能,方法的返回值会被缓存下来
     * 下一次调用该方法前,会去检查是否缓存中已经有值
     * 如果有就直接返回,不调用方法
     * 如果没有,就调用方法,然后把结果缓存起来
     * 这个注解「一般用在查询方法上」
     * <p>
     * 重点说明: 缓存注解严谨与其他筛选数据功能一起使用
     * 例如: 数据权限注解 会造成 缓存击穿 与 数据不一致问题
     * <p>
     * cacheNames 命名规则 查看 {@link CacheNames} 注释 支持多参数
     */
    @Cacheable(cacheNames = "demo:cache#60s#10m#20", key = "#key", condition = "#key != null")
    @GetMapping("/test1/{key}/{value}")
    public String test1(@PathVariable("key") String key, @PathVariable("value") String value) {
        log.warn("test1-->调用方法体 {} ",value);
        return "操作成功"+value;
    }

    @Autowired
    private IntfBatisService intfBatisService;

    @Autowired
    private IntfLogJpaService intfLogJpaService;


    @RequestMapping("/intf2")
    public IntfLogJpa intf2(){
        IntfLogJpa intfLogJpa = intfLogJpaService.getIntfLogByCaller("2");
        log.warn("IntfLogJpa {}", JSONUtil.parse(intfLogJpa));
        return intfLogJpa;
    }


}
