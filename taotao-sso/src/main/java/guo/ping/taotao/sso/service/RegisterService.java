package guo.ping.taotao.sso.service;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.pojo.TbUser;

public interface RegisterService {
    TaotaoResult checkData(String param, int type);

    TaotaoResult register(TbUser user);
}
