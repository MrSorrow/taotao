package guo.ping.taotao.mapper;

import guo.ping.taotao.pojo.TbItemParam;
import guo.ping.taotao.pojo.TbItemParamAndName;

import java.util.List;

public interface TbItemParamMapper {

    List<TbItemParamAndName> getItemParamList();

    TbItemParam getItemParamByCid(Long cid);
}
