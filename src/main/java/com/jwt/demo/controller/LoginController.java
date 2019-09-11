package com.jwt.demo.controller;

import com.jwt.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zeng
 */
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("userName") String userName) {
        return loginService.login(userId, userName);
    }

    @PostMapping("/test")
    public String test(@RequestParam("userId") String userId, @RequestParam("userName") String userName) {
        return loginService.test(userId, userName);
    }
}
