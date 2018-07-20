package guo.ping.taotao.sso.service;

import guo.ping.taotao.common.pojo.TaotaoResult;

public interface RegisterService {
    TaotaoResult checkData(String param, int type);
}
