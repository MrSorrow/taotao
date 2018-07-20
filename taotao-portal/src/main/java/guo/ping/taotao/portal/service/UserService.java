package guo.ping.taotao.portal.service;

import guo.ping.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
