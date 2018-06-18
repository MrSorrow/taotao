package guo.ping.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.IDUtils;
import guo.ping.taotao.mapper.TbItemDescMapper;
import guo.ping.taotao.mapper.TbItemMapper;
import guo.ping.taotao.mapper.TbItemParamItemMapper;
import guo.ping.taotao.pojo.TbItem;
import guo.ping.taotao.pojo.TbItemDesc;
import guo.ping.taotao.pojo.TbItemParam;
import guo.ping.taotao.pojo.TbItemParamItem;
import guo.ping.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;


    @Override
    public TbItem getItemById(Long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<TbItem> itemList = tbItemMapper.getItemList();

        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);

        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setRows(itemList);
        easyUIDataGridResult.setTotal(pageInfo.getTotal());

        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) {
        // 生成商品ID
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        // 商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        // 创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        // 插入商品表
        tbItemMapper.insert(item);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDescMapper.insert(tbItemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParam);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItemMapper.insert(tbItemParamItem);

        return TaotaoResult.ok();
    }
}
