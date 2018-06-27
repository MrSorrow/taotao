package guo.ping.taotao.rest.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.JsonUtils;
import guo.ping.taotao.mapper.TbContentMapper;
import guo.ping.taotao.pojo.TbContent;
import guo.ping.taotao.rest.component.JedisClient;
import guo.ping.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;

    @Override
    public List<TbContent> getContentList(Long cid) {
        // 添加缓存
        // 查询数据库之前先查询缓存，如果有直接返回
        try {
            //从redis中取缓存数据
            String json = jedisClient.hget(REDIS_CONTENT_KEY, cid + "");
            if (!StringUtils.isBlank(json)) {
                return JsonUtils.jsonToList(json, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<TbContent> tbContentList = tbContentMapper.getContentListByCategoryId(cid);

        // 返回结果之前，向缓存中添加数据
        try {
            jedisClient.hset(REDIS_CONTENT_KEY, cid + "", JsonUtils.objectToJson(tbContentList));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbContentList;
    }

    /**
     * 删除缓存来缓存同步
     * @param cid
     * @return
     */
    @Override
    public TaotaoResult syncContent(Long cid) {
        Long result = jedisClient.hdel(REDIS_CONTENT_KEY, cid + "");
        if (result <= 0) {
            return TaotaoResult.build(204, "没有缓存可以同步");
        }
        return TaotaoResult.ok();
    }
}
