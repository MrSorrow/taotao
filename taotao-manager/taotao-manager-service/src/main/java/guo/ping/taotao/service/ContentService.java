package guo.ping.taotao.service;

import guo.ping.taotao.common.pojo.EasyUIDataGridResult;

public interface ContentService {
    EasyUIDataGridResult getContentListByCategoryId(Long categoryId, int page, int rows);
}
