# SecondMavenTravel
重做一遍的练手项目，实现了一些简单的功能
具体如下：
1 在架构上选取了难度不大的三层架构：
  1.1 Web层
  1.2 Service层
  1.3 Dao层
2 代码的文件结构
  2.1 domain
  2.2 dao
  2.3 service
  2.4 web
  2.5 util
3 数据库表设计
  3.1 tab_user
  3.2 tab_category
  3.3 tab_route
  3.4 tab_seller
  3.5 tab_route_img
  3.6 tab_favorite
  
  
  一 项目介绍：
     在学习JavaSE与Web的相关知识后，为了强化自己对知识掌握的熟练程度，在网上找到了该项目，
     一方面该项目涉及到大量 JavaSE、数据库、HTTP协议、Servlet 等基础知识，项目整体综合性较强。
     另一方面，这个案例尚未涉及现今比较高级热门的后端开发技术栈（SSM、SpringBoot等）以及以在高并发场景下的应对策略。
     比较适合我这种具备一定Java语言和网络编程基础的同学进行学习。
     整个项目中包含了当今 Web 应用中许多典型的业务场景，例如注册、登陆、收藏等……我学习和实践的过程中，在跟随授课老师的讲解完成课上内容开发的基础上，
     自己也在课后补充了一些其它有趣的功能，同时也解决了项目中一些 BUG，这也算是对我自己学习的检验，以及加深对后端业务逻辑的理解，在做的过程中碰到了不少的错误，
     经过自己的摸爬滚打也学到了不少的东西。
  二 2 项目架构与技术选型
       项目在开发过程中采用经典的三层架构（Web - Service - Dao），且前后端分离。因此，一些实时的前端渲染不再使用 JSP 实现，而通过 AJAX 实现。各层技术选型归纳如下：

      2.1 Web 层
      Servlet：前端控制程序
      HTML：前端页面（前后端分离架构，不适用JSP）
      Filter：过滤器
      BeanUtils：数据封装
      Jackson：json 序列化工具，前后端数据通用传输格式
      2.2 Service 层
      JavaMail：Java 邮件工具
      Redis：nosql 内存数据库
      Jedis：Java 的 Jedis 客户端
      2.3 Dao 层
      mysql：关系数据库
      druid：数据库连接池
      Spring Jdbc Template：数据库连接工具

  
