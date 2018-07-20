package guo.ping.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录注册页面
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/register")
    public String shoRegist() {
        return "register";
    }
}
