package io.github.rothschil.web.controller;

import io.github.rothschil.common.constant.Constant;
import io.github.rothschil.domain.vo.UserVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Validated UserVo user) {
        return Constant.NUM_1;
    }
}
