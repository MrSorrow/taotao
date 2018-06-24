package guo.ping.taotao.rest.service.impl;

import guo.ping.taotao.mapper.TbItemCatMapper;
import guo.ping.taotao.pojo.TbItemCat;
import guo.ping.taotao.rest.pojo.CatNode;
import guo.ping.taotao.rest.pojo.ItemCatResult;
import guo.ping.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private TbItemCatMapper tbItemCatMapper;


    @Override
    public ItemCatResult getItemCatList() {
        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setData(recurrentCatNodeList(0L));
        return itemCatResult;
    }

    /**
     * 根据id递归查询
     * @param parentId
     * @return
     */
    private List recurrentCatNodeList(Long parentId) {
        List<TbItemCat> tbItemCats = tbItemCatMapper.getItemCatByParentId(parentId);

        List resultLists = new ArrayList();
        for (TbItemCat tbItemCat : tbItemCats) {
            if (tbItemCat.getIsParent()) {
                // 如果是父节点
                CatNode node = new CatNode();
                node.setUrl("/products/"+tbItemCat.getId()+".html");

                if (tbItemCat.getParentId() == 0) {
                    node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    node.setName(tbItemCat.getName());
                }

                node.setItem(recurrentCatNodeList(tbItemCat.getId()));

                resultLists.add(node);
            } else {
                // 如果是叶子节点
                String item = "/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName();
                resultLists.add(item);
            }
        }
        return resultLists;
    }
}
