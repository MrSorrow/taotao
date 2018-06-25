package guo.ping.taotao.service.impl;

import guo.ping.taotao.common.pojo.EasyUITreeNode;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.mapper.TbContentCategoryMapper;
import guo.ping.taotao.pojo.TbContentCategory;
import guo.ping.taotao.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理Service
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentId) {
        List<TbContentCategory> categories = tbContentCategoryMapper.selectTbContentCatsByParentId(parentId);
        List<EasyUITreeNode> nodes = new ArrayList<>();

        for (TbContentCategory contentCategory : categories) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public TaotaoResult insertCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);  // 1正常 2删除
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        tbContentCategoryMapper.insertCategory(contentCategory);

        TbContentCategory parentContentCategory = tbContentCategoryMapper.selectTbContentCatById(parentId);
        if (!parentContentCategory.getIsParent()) {
            parentContentCategory.setIsParent(true);
            parentContentCategory.setUpdated(new Date());
            tbContentCategoryMapper.updateContentCategoryById(parentContentCategory);
        }
        return TaotaoResult.ok(contentCategory.getId());
    }
}
