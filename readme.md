# 淘淘商城
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

## 环境配置
1. Win10
2. Java 1.8
3. Tomcat 7
4. Mysql 5.7
5. IDEA 2017.2

## 教程
### 聚合工程搭建
1. 参考文档：[文章1](https://blog.csdn.net/wb8878/article/details/77162078)、[文章2](https://blog.csdn.net/for_my_life/article/details/78939078)、[文章3](https://blog.csdn.net/yerenyuan_pku/article/details/72669269)；
2. 注意taotao-parent的依赖是<dependencyManagement></dependencyManagement>包裹的；
3. 运行时需要用maven来install taotao-parent和taotao-common。

### 提交github仓库
1. 参考文档：[git提交到github](https://blog.csdn.net/a695017449/article/details/26103761)

###