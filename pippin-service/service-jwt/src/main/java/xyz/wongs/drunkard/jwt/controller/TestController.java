package xyz.wongs.drunkard.jwt.controller;

import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.jwt.po.User;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/7 - 19:19
 * @Version 1.0.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @ResponseResult
    @GetMapping("/")
    public User login(){
        throw new RuntimeException();
    }

}
