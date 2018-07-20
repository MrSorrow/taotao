package guo.ping.taotao.sso.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.CookieUtils;
import guo.ping.taotao.common.utils.JsonUtils;
import guo.ping.taotao.mapper.TbUserMapper;
import guo.ping.taotao.pojo.TbUser;
import guo.ping.taotao.sso.component.JedisClient;
import guo.ping.taotao.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    /**
     * 用户登录服务
     * @param username 用户名
     * @param password 密码
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        List<TbUser> users = userMapper.selectUserByNameOrPwd(paramMap);

        if (users == null || users.isEmpty()) {
            return TaotaoResult.build(400, "该用户不存在");
        }

        TbUser tbUser = users.get(0);
        // 校验密码
        if (!tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return TaotaoResult.build(400, "密码错误");
        }

        // 登录成功
        String token = UUID.randomUUID().toString();
        tbUser.setPassword(null);
        jedisClient.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objectToJson(tbUser));
        jedisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);

        // Token写入cookie 浏览器关闭就过期
        CookieUtils.setCookie(request, response, "TT_TOKEN", token);

        return TaotaoResult.ok(token);
    }
}
