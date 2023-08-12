## 黑马旅游网--javaweb练手项目

------

> 技术栈：Java+servlet+前端三件套+mysql+redis（用于缓存优化）



### 运行步骤

1. 将项目克隆到本地idea中，`https://github.com/13dghv14ccjhffgvx/travlel-master.git`

   ![image-20230812100311894](https://gitee.com/huang-jintong/csdn-blog-gallery/raw/master/img/image-20230812100311894.png)

   

2. 运行resources包下的sql文件，生成初始数据

   

3. 修改resources包下的druid.properties，jedis.properties文件，完成对mysql、redis相关参数的配置

   ![image-20230812100828865](https://gitee.com/huang-jintong/csdn-blog-gallery/raw/master/img/image-20230812100828865.png)

   

4. 打开pom.xml文件，导入相关的jar包

   

5. 右击项目，使用pom文件中的tomcat插件运行项目

   ![image-20230812101238268](https://gitee.com/huang-jintong/csdn-blog-gallery/raw/master/img/image-20230812101238268.png)

   

==注意：==

> 1. redis服务一定要记得开启
>
> 2. 测试账号：
>
>    ​	用户名：zhangsan		密码：12345678