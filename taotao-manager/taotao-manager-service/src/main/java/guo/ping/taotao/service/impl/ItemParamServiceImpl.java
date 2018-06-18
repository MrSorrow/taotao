package guo.ping.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.mapper.TbItemParamMapper;
import guo.ping.taotao.pojo.TbItemParam;
import guo.ping.taotao.pojo.TbItemParamAndName;
import guo.ping.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<TbItemParamAndName> itemParamList = tbItemParamMapper.getItemParamList();

        PageInfo<TbItemParamAndName> pageInfo = new PageInfo<>(itemParamList);
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

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        int result = tbItemParamMapper.insertItemParam(tbItemParam);
        if (result > 0) {
            return TaotaoResult.ok();
        } else {
            return TaotaoResult.build(400, "新增商品规格参数失败");
        }
    }
}
