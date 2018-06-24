package guo.ping.taotao.service;

import guo.ping.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ContentCatService {

    List<EasyUITreeNode> getContentCatList(Long parentId);
}
