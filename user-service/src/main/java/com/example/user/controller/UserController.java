package com.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/info")
    public String getUserInfo(){

        String k = "成功获取到 user info";
        log.info(k);
        return k;
    }
}
