# Spring01

=== 02 ===

Spring给出的对象有两种方式：1. Singleton（默认） 2. Prototype （每次都new）

Singleton说的是在以下作用域中单独一个：websocket，request，session，application等基于运行环境的生命周期
websocket是网页的长连接，基于浏览器向服务器建立的长连接，当这次的连接断开的时候，对象消失。websocket基于TCP
request，继续请求
session，登陆了就给一个对象，当前登陆就new这一个
application，基于应用程序的，一旦服务启动，就只new出来这么一个对象，之后一直就是它了,这里单指spring的applicationContext
一般来说网络连接都应该是长连接，但是由于http协议产生的时候网速慢（最快的56k猫），网络资源稀缺，所以被设计成了短连接，连完了拿
到页面之后就断开。http早期设计就是为了节省资源，现在情况有点变了。

Struts2之后，Controller（Servlet）和 Service 和 Dao 层都是单例的。POJO，Entity等不是单例的，因为他们不归spring管理
（view我们不去管他）。整套MVC里面所有的类都归spring管理，单例。而这样会出现什么问题？
为什么用单例？性能优化。安全问题？并发情况下有问题，很大，webapp的容器，比如Tomcat，在每一个request来的时候新开一个线程，
这样就造成了对象只有一个但是线程却有多个，每个线程有对象属性的ThreadLocal来隔离不同的线程。如果单例对象里面有成员变量而很多线程
都去修改其中的值的话，就出现data race，所以要求Controller，service，dao里面尽量别有状态数据的成员变量，如果有，要非常小心地
使用，会有线程安全的问题。比如JDBC的Connection对象都放在ThreadLocal里（Hibernate的Session）以避免多线程修改原Connection
的状态成员变量。总之MVC中的单例对象里面不写状态数据的话是不会出问题的

Spring只是把对象以Singleton的方式创建出来了，Spring MVC包装了Servlet。Spring MVC隔离用户之间的状态数据。一次request开一
个线程，一个线程处理多个用户的请求是不现实的

循环依赖，引发内存无法释放的问题。循环引用中各个对象都永远不会被GC，因为引用计数器不为0。对于Spring容器来说，他维护着一个“注册表”，
对于Singleton，先new处对象在set属性，但是对于Prototype，一看有引用，先去把被引用的创建出来，这样就在循环中无限转圈了。Spring
会检测prototype的循环依赖，不让这么干。Singleton在Spring里跟我们平常写代码是一样的，先都new出来再set值.  如果没用Spring框架，
很有可能内存泄露，spring目的就是避免循环引用，单例不做这种检查，因为占用的内存少。循环中一旦getBean里面的那一个是单例，所有引用链/
环上的对象都成了单例，单例的引用也是单例（spring内部机制的结果）. depends-on循环了，也会报错

=== 03 ===

Springboot甚至可以用来做微信公众号的开发。

lazy-init就是在Spring注册表的那个Map中，可以先创建出来但是value还是null，什么时候getBean用到了，什么时候new对象

<property></property>下的<value></value>相当于"", <null></null>是null值
@Autowired是按照byType的方式注入属性的，有两个相同的Type的对象都注册了的话Spring启动的时候会报错
这时就应该按照name进行注入，可以写@Component("NAME")和@Autowired下面写@Qualifier("NAME")
Entity类型的类，也可以写@Compoennt让spring管起来，但是由于要线程安全，所以肯定不能是单例，所以要写在@Component的下面写@Scope("prototype")
属性的注入可以用@Value("USERNAME")来直接在类里面注入，spring会自动匹配类型

Spring AOP是通过JDK的Proxy和CGLib实现的，这两个的底层都是靠ASM


=== 04 ===

Kubernates可以不会动手搭建或者写脚本，但要知道是干嘛的，就是比Docker更强一些，有些新的思想在里面

在这里配置一个Springboot新项目：https://start.spring.io  Group相当于报的名称，Artifact相当于项目名称.SpringBoot是SpringCloud的基础
Springboot学完了之后，其他框架就基本上是调用API了
需要引入Web -> Spring Web Starter 会带来web功能，Spring，spring mvc，tomcat，点击Generate the Project之后会下载一个项目文件夹，
还有test依赖和SpringBoot和maven集成的插件
查看其pom.xml文件，所有的依赖都在spring-boot-starter-web里了。多个依赖的pom文件打包成了一个spring-boot-starter-web。Spring提供的各个
starter内部就避免了依赖的冲突。一并引入的还有jboss，jackson，log4j，asm，junit，hibernate validator

在STS/eclipse中建立该maven项目：右键，import，maven，Exsiting Project，然后选择项目文件夹，然后会引入依赖构建项目. static文件夹会放静态
文件比如html，css，js 图片等。templates：spring mvc的时候官方推荐jsp做前端渲染，但后来已经有人开始用模版引擎了，比如Thymeleaf Free Marker等，
template 会放空模版，带标记的空模版，渲染的时候从template中读取。

启动SpringBoot项目，两种方式：1. 运行主入口程序，带main 方法的那个，右键，Run As -> Java Application
SpringBoot有一套完整的内置的默认配置（一部分爽点在这里），比如Tomcat的web.xml和spring的xml的配置。想个性化的配置某些框架，还可以通过
applicatoin.properties来配置这样会覆盖原来的默认配置，比如spring和spring mvc整合的时候，会去写一些配置管理Controller，再如修改web.xml。

内嵌容器化的web项目，比较有颠覆性，重新使得Tomcat焕发了活力。大数据基本思想：1.分而治之 2. 计算向数据移动。已经打包好了web服务器tomcat这个运行环境
然后扔到服务器（只需要有Javase）上，好处是不需要在二次调整，而且更新项目的时候，和运维分离开了，每个项目有一个tomcat，想想就爽。运行的时候就是一个
普普通通的Jar包。方便运维；项目启动成本降低，批量开关服务器，开关一个项目不会影响其他的程序，因为不share tomcat，对于运维监控也更加简单；项目更轻量化，
容器级别就隔离开了，更适合作为服务（把项目拆得更加细粒度，一个项目里只完成一些简简单单的小功能而已，一台不够可以复制多个副本，再多台服务器上运行）！

项目上单击右键，然后Run As -> Maven Install就会在target文件夹下生成项目打包的jar文件, 双击Jar文件或者在命令行下执行
java -jar ./FirstSpringBoot-0.0.1-SNAPSHOT.jar  即可运行，然后去浏览器，查看http://localhost:8080即可证实。想结束程序的话，就jps，杀进程，
然后再重新启动即可.jar文件可以拿到任意一台机器，只要装了jdk/jre，不用安装启动tomcat或下载jar包，就可以用启动程序。传统的Tomcat项目要运行Tomcat再把
war（其实跟Jar一样，是个zip包，区别是war是给web用的，遵循web标准，会被web容器自动解压开，放到对应的目录下）传上去在其中运行. Restful是一种约定好的
访问接口的方式.

另一种启动方式：项目上面右键，然后Run As然后选择SpringBoot Application。

更改Tomcat的监听端口：在application.properties文件中写：server.port=80 然后重新启动，再访问浏览器的时候就不用指定8080端口了
更改项目访问的主路径：在application.properties文件中写：server.servlet.context-path=/boot，然后重启就在浏览器可以通过http://localhost/boot/
访问了
更改banner：在src/main/resources目录下新建banner.txt文件，在其中写入banner内容即可

SpringBoot下的Controller：在类的脑袋上面写@RestController, 他是一个@ResponseBody，不经前端渲染的数据
配置热部署：项目上右键，Spring，Add Dev Tools即可。会在pom文件中加一个spring-boot-devtools依赖。注意这不叫热部署，只是自动触发了重启



=== 05 ===

基于TCP的网络连接都是长连接，短连接要断开连接。http1.0都是短连接吗，要断开，http1.1有一个keep alive的flag，浏览器支持这个选项的话，可以再一次tcp连接下
完成多次的request和response请求，连接一旦断开，那还要重新建立.

看源码方法的小窍门是打断点，跟进去，看怎么走的。看源码找不到方向是很正常的。