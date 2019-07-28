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

Springboot甚至可以用来做微信公众号的开发
