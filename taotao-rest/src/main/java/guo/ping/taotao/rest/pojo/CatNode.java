package guo.ping.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 商品类目分类节点
 */
public class CatNode {

    @JsonProperty("u")
    private String url;

    @JsonProperty("n")
    private String name;

    @JsonProperty("i")
    private List<?> item;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
