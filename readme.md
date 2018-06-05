# 淘淘商城
项目源自于某培训机构，重新利用IDEA实现。

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