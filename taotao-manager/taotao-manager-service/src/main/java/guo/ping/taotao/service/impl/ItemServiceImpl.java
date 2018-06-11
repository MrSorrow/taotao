package guo.ping.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.mapper.TbItemMapper;
import guo.ping.taotao.pojo.TbItem;
import guo.ping.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

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
}
