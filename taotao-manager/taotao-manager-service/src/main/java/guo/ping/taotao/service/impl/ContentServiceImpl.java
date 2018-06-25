package guo.ping.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.mapper.TbContentMapper;
import guo.ping.taotao.pojo.TbContent;
import guo.ping.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public EasyUIDataGridResult getContentListByCategoryId(Long categoryId, int page, int rows) {
        PageHelper.startPage(page, rows);

        List<TbContent> tbContents = new ArrayList<>();
        if (categoryId == 0L) {
            tbContents = tbContentMapper.getAllContentList();
        } else {
            tbContents = tbContentMapper.getContentListByCategoryId(categoryId);
        }

        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setRows(tbContents);
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        return easyUIDataGridResult;
    }
}
