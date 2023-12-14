package io.github.rothschil.web.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Slf4j
@Controller
public class BaseController {

    @RequestMapping("/")
    public String index(){
        log.warn("index {}", new Date());
        return "redirect:/iserv/index";
    }
}
