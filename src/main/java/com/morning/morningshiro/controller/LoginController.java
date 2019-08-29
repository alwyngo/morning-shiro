package com.morning.morningshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/doLogin")
    public String login(UsernamePasswordToken token, HttpServletRequest request, HttpServletResponse response) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            response.sendRedirect("/index");
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request, HttpServletResponse response) {
        Principal userPrincipal = request.getUserPrincipal();
        // 获取sessionId
        HttpSession session = request.getSession(false);
        String id = session.getId();
        System.out.println("session" + id);

        //获取request中的cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ":" + cookie.getValue());
        }

        //添加cookie
        Cookie cookie = new Cookie("username", userPrincipal.getName());
        cookie.setMaxAge(1000);
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setVersion(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "admin";
    }

    /**
     * @desc 默认跳转hello页面
     * @param
     * @author lxu003
     * @date   2019/8/29 14:24
     */
    @RequestMapping("hello")
    public void hello() {
        System.out.println("hello");
    }
}
