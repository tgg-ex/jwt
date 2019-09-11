package com.jwt.demo.service;

/**
 * @author zeng
 * <p>
 * 登录业务层接口
 */
public interface ILoginService {

    /**
     * 用户登录
     *
     * @param userId   用户id
     * @param userName 用户名称
     */
    String login(String userId, String userName);


    /**
     * 测试方法
     *
     * @param userId   用户id
     * @param userName 名称
     */
    String test(String userId, String userName);
}
