package guo.ping.taotao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guo.ping.taotao.mapper.TbItemMapper;
import guo.ping.taotao.pojo.TbItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PageHelperTest {

    @Test
    public void testPageHelper() throws Exception {
        //1、获得mapper代理对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");

        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);

        //2、设置分页
        PageHelper.startPage(1, 30);

        List<TbItem> itemList = itemMapper.getItemList();
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);

        long total = pageInfo.getTotal();
        System.out.println("total:" + total);
        int pages = pageInfo.getPages();
        System.out.println("pages:" + pages);
        int pageSize = pageInfo.getPageSize();
        System.out.println("pageSize:" + pageSize);

    }

}
