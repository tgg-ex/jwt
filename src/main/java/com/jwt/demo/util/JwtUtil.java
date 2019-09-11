package com.jwt.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zeng
 * <p>
 * jwt工具类
 */
@Slf4j
@Data
@Component
public class JwtUtil {


    /**
     * 加密秘钥
     */
    private String secret = "2rewrrewrwe12";

    /**
     * 有效时间
     */
    private Long expire = 1000L;


    /**
     * 生成Token签名
     *
     * @param userId 用户ID
     * @return 签名字符串
     * @author geYang
     * @date 2018-05-18 16:35
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();
        // 过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setId(String.valueOf(userId)) //把用户id放入jwt
                .setSubject(String.valueOf(userId))//jwt数据载体
                .setExpiration(expireDate) //过期时间
                .signWith(SignatureAlgorithm.HS256, getSecret())
                .compact();

    }

    /**
     * 安全中心token解析
     *
     * @param token token
     * @return token中的信息
     */
    public Jws<Claims> parse(String token) {
        return parse(token, getSecret());
    }

    /**
     * 安全中心token解密
     *
     * @param token  token
     * @param source 秘钥
     * @return token中的信息
     */
    public static Jws<Claims> parse(String token, String source) {
        return Jwts.parser()
                .setSigningKey(source)
                .parseClaimsJws(token);
    }

}
