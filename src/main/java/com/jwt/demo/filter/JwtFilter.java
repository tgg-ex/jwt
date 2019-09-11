package com.jwt.demo.filter;

import com.jwt.demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


/**
 * @author zeng
 * <p>
 * Token验证拦截器
 */
@Component
@Slf4j
public class JwtFilter extends GenericFilterBean {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> WHITE_LIST = Arrays.asList(
            "/login"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        String urlPath = request.getRequestURI();
        if (isUrl(urlPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = request.getHeader("token");
        if (token == null) {
            response.setStatus(500);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().print("token");
        }
        try {
            Jws<Claims> parse = jwtUtil.parse(token);
            Claims body = parse.getBody();
            body.getId();
            if (body.getId() != null) {
                log.info("id：" + body.getId() + "    name：" + body.getSubject());
                servletRequest.setAttribute("id", body.getId());
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } catch (SignatureException ex) {
            response.setStatus(500);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().print("token错误");
        }

    }

    public boolean isUrl(String urlPath) {
        return WHITE_LIST.contains(urlPath);
    }
}
