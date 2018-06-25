package guo.ping.taotao.service;

import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.pojo.TbContent;

public interface ContentService {
    EasyUIDataGridResult getContentListByCategoryId(Long categoryId, int page, int rows);

    TaotaoResult insertContent(TbContent tbContent);
}
