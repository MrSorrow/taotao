package guo.ping.taotao.rest.service;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    List<TbContent> getContentList(Long cid);
    TaotaoResult syncContent(Long cid);
}
