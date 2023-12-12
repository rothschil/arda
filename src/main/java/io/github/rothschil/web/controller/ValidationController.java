package io.github.rothschil.web.controller;

import cn.hutool.json.JSONUtil;
import io.github.rothschil.common.constant.Constant;
import io.github.rothschil.domain.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ValidationController {

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Validated UserVo user) {
        log.warn("UserVo ={}", JSONUtil.parse(user));
        return Constant.NUM_1;
    }
}
