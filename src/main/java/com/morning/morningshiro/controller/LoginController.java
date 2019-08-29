package com.morning.morningshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        // 获取sessionId
        HttpSession session = request.getSession(false);
        String id = session.getId();
        System.out.println(id);
        return "admin";
    }
}
