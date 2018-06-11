package guo.ping.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示首页
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }
}
