package com.morning.morningshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * @desc 添加ResponseBody注解避免跳转到logout页面
     * @param
     * @author lxu003
     * @date   2019/8/29 14:24
     */
    @RequestMapping("/logout")
    public String logout () {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
