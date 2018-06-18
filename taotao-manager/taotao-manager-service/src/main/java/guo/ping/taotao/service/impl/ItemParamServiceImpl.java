package guo.ping.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.mapper.TbItemParamMapper;
import guo.ping.taotao.pojo.TbItemParam;
import guo.ping.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<TbItemParam> itemParamList = tbItemParamMapper.getItemParamList();

        PageInfo<TbItemParam> pageInfo = new PageInfo<>(itemParamList);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setRows(itemParamList);
        easyUIDataGridResult.setTotal(pageInfo.getTotal());

        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        TbItemParam itemParam = tbItemParamMapper.getItemParamByCid(cid);
        if (itemParam != null) {
            return TaotaoResult.ok(itemParam);
        }
        return TaotaoResult.ok();
    }
}
