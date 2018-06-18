package guo.ping.taotao.mapper;

import guo.ping.taotao.pojo.TbItemParam;

import java.util.List;

public interface TbItemParamMapper {

    List<TbItemParam> getItemParamList();

    TbItemParam getItemParamByCid(Long cid);
}
