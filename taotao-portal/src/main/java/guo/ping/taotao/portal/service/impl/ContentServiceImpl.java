package guo.ping.taotao.portal.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.HttpClientUtil;
import guo.ping.taotao.common.utils.JsonUtils;
import guo.ping.taotao.pojo.TbContent;
import guo.ping.taotao.portal.pojo.AdNode;
import guo.ping.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;
    @Value("${REST_CONTENT_AD1_CID}")
    private String REST_CONTENT_AD1_CID;

    @Override
    public String getAdList() {
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);
        TaotaoResult taotaoResult = TaotaoResult.formatToList(json, TbContent.class);
        List<TbContent> contents = (List<TbContent>) taotaoResult.getData();

        List<AdNode> adNodes = new ArrayList<>();
        for (TbContent content : contents) {
            AdNode adNode = new AdNode();
            adNode.setHeight(240);
            adNode.setWidth(670);
            adNode.setSrc(content.getPic());

            adNode.setHeightB(240);
            adNode.setWidthB(550);
            adNode.setSrcB(content.getPic2());

            adNode.setAlt(content.getSubTitle());
            adNode.setHref(content.getUrl());

            adNodes.add(adNode);
        }
        return JsonUtils.objectToJson(adNodes);
    }
}
