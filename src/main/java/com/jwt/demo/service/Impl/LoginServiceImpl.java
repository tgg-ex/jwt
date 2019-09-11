package com.jwt.demo.service.Impl;

import com.jwt.demo.service.ILoginService;
import com.jwt.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zeng
 * <p>
 * 登录业务层实现类
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(String userId, String userName) {
        return jwtUtil.generateToken(Integer.parseInt(userId));
    }

    @Override
    public String test(String userId, String userName) {
        return "ok";
    }
}
