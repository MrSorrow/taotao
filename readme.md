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
4. 添加Jar包时，需要重新安装parent、common、manager工程。

### 添加商品提交表单
1. 参考文章：[实现添加商品功能](https://blog.csdn.net/yerenyuan_pku/article/details/72810937)

### 商品规格参数
1. 为何使用单表存储json冗余方式取代多表关联？[商品规格的数据库设计和实现流程](https://blog.csdn.net/qq_40369829/article/details/79515596)
2. 实现方式：[商品规格参数的表结构设计](https://blog.csdn.net/qq_1017097573/article/details/68501687)
3. 查询商品规格参数：类似商品列表分页展示，额外包含商品类目名称的一对一多表查询。

### 维护商品规格参数
1. 提交规格参数模板
2. 规格参数模板使用：维护具体商品时根据分类id查询规格参数模板
3. 保存具体商品规格参数：在商品表单提交之前，先把规格参数的信息，转换成json数据插入表中。
4. 展示规格参数

### 创建taotao-rest、taotao-portal工程
1. 创建两个webapp的maven工程，配置参见最开始项目搭建过程。
2. 修改tomcat端口号。

### 前端首页显示
1. 参考文档：[前台系统工程搭建](https://blog.csdn.net/yerenyuan_pku/article/details/72825533)
2. 打包跳过测试：[IDEA中maven打包跳过Junit Test](https://www.cnblogs.com/ningheshutong/p/6380933.html)

### 首页类目展示
1. 跨域处理：[jsonp的原理及两种实现方式](https://blog.csdn.net/yerenyuan_pku/article/details/73296428)

### CMS系统实现
1. 内容分类管理树形列表显示
2. 新增内容分类节点
3. 内容详情分页查询
4. 新增内容
5. 展示轮播图

### Redis缓存
1. CentOS6.4搭建Redis单机与集群
2. 利用Redis实现缓存功能
3. 参考文档：[Redis集群搭建与缓存实现](https://blog.csdn.net/bskfnvjtlyzmv867/article/details/80834857)

### 创建taotao-search工程
1. 创建一个个webapp的maven工程，类似taotao-rest；
