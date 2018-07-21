package guo.ping.taotao.portal.interceptor;

import guo.ping.taotao.pojo.TbUser;
import guo.ping.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser user = userService.getUserByToken(httpServletRequest, httpServletResponse);
        // 如果用户处于未登录状态，跳转到登录页面
        if (user == null) {
            httpServletResponse.sendRedirect(SSO_LOGIN_URL + "?redirectUrl=" + httpServletRequest.getRequestURL());
            return false;
        }
        // 如果取到用户信息，放行
        httpServletRequest.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
