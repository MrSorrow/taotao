package guo.ping.taotao.service.impl;

import guo.ping.taotao.common.pojo.EasyUITreeNode;
import guo.ping.taotao.mapper.TbItemCatMapper;
import guo.ping.taotao.pojo.TbItemCat;
import guo.ping.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        List<EasyUITreeNode> result = new ArrayList<>();
        List<TbItemCat> itemCatList = tbItemCatMapper.getItemCatByParentId(parentId);

        for (TbItemCat tbItemCat : itemCatList) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(tbItemCat.getId());
            easyUITreeNode.setState(tbItemCat.getIsParent() ? "closed" : "opened");
            easyUITreeNode.setText(tbItemCat.getName());
            result.add(easyUITreeNode);
        }
        return result;
    }
}
