package guo.ping.taotao.mapper;

import guo.ping.taotao.pojo.TbUser;

import java.util.List;
import java.util.Map;

public interface TbUserMapper {

    List<TbUser> selectByKey(Map<String, Object> paramMap);
}