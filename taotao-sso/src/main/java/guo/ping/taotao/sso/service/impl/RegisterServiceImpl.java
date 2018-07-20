package guo.ping.taotao.sso.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.mapper.TbUserMapper;
import guo.ping.taotao.pojo.TbUser;
import guo.ping.taotao.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public TaotaoResult checkData(String param, int type) {
        // 根据数据类型检查数据
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("param", param);
        paramMap.put("type", type);
        List<TbUser> tbUsers = userMapper.selectByKey(paramMap);
        if (tbUsers == null || tbUsers.isEmpty()) {
            return TaotaoResult.ok(true);
        } else {
            return TaotaoResult.ok(false);
        }
    }
}
