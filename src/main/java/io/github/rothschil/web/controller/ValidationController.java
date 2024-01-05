package io.github.rothschil.web.controller;

import cn.hutool.json.JSONUtil;
import io.github.rothschil.common.constant.Constant;
import io.github.rothschil.common.utils.RestUtils;
import io.github.rothschil.domain.mybatis.entity.IntfConfEntity;
import io.github.rothschil.domain.vo.PostRespVo;
import io.github.rothschil.domain.vo.UserPostVo;
import io.github.rothschil.domain.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ValidationController {

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Validated UserVo user) {
        log.warn("UserVo ={}", JSONUtil.parse(user));
        return Constant.NUM_1;
    }

    @PostMapping("/post")
    public PostRespVo post(@RequestBody @Validated UserPostVo user) {
        log.warn("UserPostVo ={}", JSONUtil.parse(user));
        IntfConfEntity entity = new IntfConfEntity();
        entity.setAddress("http://192.168.88.130:11200/index");
        entity.setTimeout(4000);
        entity.setInterfaceName("wongs");
        Mono<PostRespVo>  mono = (Mono<PostRespVo>)RestUtils.post(entity,JSONUtil.toJsonStr(user),new HttpHeaders(), PostRespVo.class);
        return mono.block();
    }
}
