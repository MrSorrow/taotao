# 淘淘商城（IDEA实现）
项目源自于某培训机构，重新利用IDEA实现。项目涉及功能：
1. 后台聚合工程搭建，ssm框架整合;
2. 商品管理，商品列表展示，商品添加中的商品类目选择；
3. 商品添加：上传图片，Nginx，FastDFS。
4. 商品添加实现：富文本编辑器的使用、商品的规格参数实现；
5. 前台系统搭建：展示首页、展示商品类目；
6. 首页大广告位的展示：Cms系统实现、redis缓存（集群）首页大广告位展示；
7. 搜索功能的实现，使用solr实现（solr集群）；
8. 商品详情页面实现，网页静态化freemaker实现；
9. 单点登录系统实现，session共享；
10. 购物车、订单系统；
11. Quartz任务调度框架；项目部署、项目总结、面试中的问题。

## 技术选型
1. 数据库：mysql
2. Dao层：mybatis、数据库连接池（德鲁伊druid）
3. 缓存：redis
4. 搜索：solr
5. Service层：spring
6. 表现层：springmvc、jstl、EasyUI、jsp、freemaker
7. 图片服务器：FastDFS（分布式文件系统）
8. 反向代理服务器：nginx
9. 定时器：Quartz
10. Web服务器：tomcat
11. 工程管理：maven

## 环境配置
1. Win10、CentOS6.4
2. Java 1.8
3. Tomcat 7
4. Mysql 5.6
5. IDEA 2017.2

## 教程
总体参考专栏博客：[项目实战之淘淘商城](https://blog.csdn.net/column/details/15737.html?&page=5)
### 聚合工程搭建
1. 参考文档：[文章1](https://blog.csdn.net/wb8878/article/details/77162078)、[文章2](https://blog.csdn.net/for_my_life/article/details/78939078)、[文章3](https://blog.csdn.net/yerenyuan_pku/article/details/72669269)；
2. 注意taotao-parent的依赖是\<dependencyManagement>\</dependencyManagement>包裹的；
3. 运行时需要用maven来install taotao-parent和taotao-common。

### 提交github仓库
1. 参考文档：[git提交到github](https://blog.csdn.net/a695017449/article/details/26103761)

### SSM整合测试
1. taotao-manager-web缺失java和resources目录：[文章](https://blog.csdn.net/u014756827/article/details/52497572)
2. mapper文件拷贝缺失，参考：[文章](https://blog.csdn.net/xiaoshubiao/article/details/78563037)。其中拷贝到resources目录方法，需要新建和包名一致的目录并拷贝。
3. 项目启动不起来，因为逆向工程生成的代码有问题。参考：[文章](https://bbs.csdn.net/topics/392273297)。索性不用逆向工程，纯手写！
4. Idea @Autowired取消提示：[文章](https://blog.csdn.net/qqyouhappy/article/details/52366198)

### 首页展示
1. 参考文档：[展示后台管理页面](https://blog.csdn.net/yerenyuan_pku/article/details/72773063)
2. 静态资源映射问题解决。

### 商品列表分页展示
1. 参考文档：[MyBatis分页插件(PageHelper)的使用以及商品列表展示](https://blog.csdn.net/yerenyuan_pku/article/details/72774381)

### 添加商品类目选择
1. 参考文档：[商品类目选择的实现](https://blog.csdn.net/yerenyuan_pku/article/details/72786989)
2. 默认id参数

### 图片服务器
1. 为什么要额外架设图片服务器：集群的图片没有同步;
2. FastDFS搭建图片服务器：JavaEE进阶——[CentOS开发环境搭建](https://blog.csdn.net/bskfnvjtlyzmv867/article/details/80679324)、[Nginx反向代理负载均衡](https://blog.csdn.net/bskfnvjtlyzmv867/article/details/80682470)、[FastDFS实现分布式文件系统](https://blog.csdn.net/bskfnvjtlyzmv867/article/details/80714435)
3. service和controller实现：[实现图片上传功能](https://blog.csdn.net/yerenyuan_pku/article/details/72808000)