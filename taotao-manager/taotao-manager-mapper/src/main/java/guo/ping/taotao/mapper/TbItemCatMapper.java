package guo.ping.taotao.mapper;

import guo.ping.taotao.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {
    List<TbItemCat> getItemCatByParentId(long parentId);

    String getItemCatNameById(long id);
}
