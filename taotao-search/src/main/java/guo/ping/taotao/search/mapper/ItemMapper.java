package guo.ping.taotao.search.mapper;

import guo.ping.taotao.search.pojo.SearchItem;

import java.util.List;

/**
 * Solr服务的导入数据
 */
public interface ItemMapper {

    List<SearchItem> getItemList();
}
