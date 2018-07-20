package guo.ping.taotao.sso.service;

import guo.ping.taotao.common.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
