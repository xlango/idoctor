# idoctor
基于SSM的医院综合管理系统</br>
（1）使用PageHelper分页</br>
（2）Redis</br>
  Redis运行：在根目录下执行redis-server.exe redis.windows.conf</br>
     涉及问题：在数据库数据更新后如何更新Redis？----在更新数据时同时删除Redis</br>
    参考：https://blog.csdn.net/xsyl08/article/details/78773760</br>
 （3）整合swagger-ui</br>
 maven引入所需jar包:</br>
<artifactId>springfox-swagger-ui</artifactId></br>
<artifactId>springfox-swagger2</artifactId></br>
自定义配置文件：/idoctor/src/main/java/com/idoctor/utils/SwaggerConfig.java</br>
(4)使用MD5加密工具</br>
(5)Mybatis批量操作</br>
我的方法：前端想后端传递List<>参数时采用传Json String方式，使用JSONArray.fromObject(str)在后端转换为Json对象</br>