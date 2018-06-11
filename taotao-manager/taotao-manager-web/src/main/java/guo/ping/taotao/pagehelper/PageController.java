package guo.ping.taotao.pagehelper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
