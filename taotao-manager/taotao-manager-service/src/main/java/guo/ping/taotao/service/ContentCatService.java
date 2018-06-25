package guo.ping.taotao.service;

import guo.ping.taotao.common.pojo.EasyUITreeNode;
import guo.ping.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCatService {

    List<EasyUITreeNode> getContentCatList(Long parentId);
    TaotaoResult insertCategory(Long parentId, String name);
}
