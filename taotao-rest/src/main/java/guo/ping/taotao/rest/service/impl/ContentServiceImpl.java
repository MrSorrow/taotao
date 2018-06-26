package guo.ping.taotao.rest.service.impl;

import guo.ping.taotao.mapper.TbContentMapper;
import guo.ping.taotao.pojo.TbContent;
import guo.ping.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> getContentList(Long cid) {
        return tbContentMapper.getContentListByCategoryId(cid);
    }
}
