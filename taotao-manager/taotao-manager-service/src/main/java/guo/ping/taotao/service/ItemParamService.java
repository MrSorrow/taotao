package guo.ping.taotao.service;

import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {
    EasyUIDataGridResult getItemParamList(int page, int rows);
    TaotaoResult getItemParamByCid(Long cid);
    TaotaoResult insertItemParam(Long cid, String paramData);
}
