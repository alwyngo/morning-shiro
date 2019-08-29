package com.morning.morningshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String login(UsernamePasswordToken token) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "";
    }

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

        return "admin";
    }
}
