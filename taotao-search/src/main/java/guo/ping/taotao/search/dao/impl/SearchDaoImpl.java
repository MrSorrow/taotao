package guo.ping.taotao.search.dao.impl;

import guo.ping.taotao.search.dao.SearchDao;
import guo.ping.taotao.search.pojo.SearchItem;
import guo.ping.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery solrQuery) throws SolrServerException {
        // 执行查询
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList responseResults = response.getResults();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument document : responseResults) {
            SearchItem item = new SearchItem();
            item.setCategoryName((String) document.get("item_category_name"));
            item.setId((String) document.get("id"));
            item.setImage((String) document.get("item_image"));
            item.setPrice((Long) document.get("item_price"));
            item.setSellPoint((String) document.get("item_sell_point"));
            // 取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String itemTitle = "";
            if (list != null && list.size() > 0) {
                //取高亮后的结果
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) document.get("item_title");
            }
            item.setTitle(itemTitle);

            itemList.add(item);
        }
        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(itemList);
        // 查询结果总数量
        searchResult.setRecordCount(responseResults.getNumFound());
        return searchResult;
    }
}
