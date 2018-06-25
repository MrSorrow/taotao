package guo.ping.taotao.mapper;

import guo.ping.taotao.pojo.TbContent;

import java.util.List;

public interface TbContentMapper {
    List<TbContent> getContentListByCategoryId(Long categoryId);

    List<TbContent> getAllContentList();

    void insertContent(TbContent tbContent);
}
