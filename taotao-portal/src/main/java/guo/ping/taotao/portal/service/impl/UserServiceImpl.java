package guo.ping.taotao.portal.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.CookieUtils;
import guo.ping.taotao.common.utils.HttpClientUtil;
import guo.ping.taotao.pojo.TbUser;
import guo.ping.taotao.portal.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理Service
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;

    @Override
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 从cookie中取出Token
            String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
            if (StringUtils.isBlank(token)) {
                return null;
            }

            // 调用sso服务
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
            TaotaoResult result = TaotaoResult.format(json);
            if (result.getStatus() != 200) {
                return null;
            }

            // 取得用户数据
            result = TaotaoResult.formatToPojo(json, TbUser.class);
            TbUser user = (TbUser) result.getData();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
