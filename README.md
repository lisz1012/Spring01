# Spring01

Spring给出的对象默认有两种：1. Singleton 2. Prototype

Singleton说的是在以下作用域中单独一个：websocket，request，session，application等基于运行环境的生命周期
websocket是网页的长连接，基于浏览器向服务器建立的长连接，当这次的连接断开的时候，对象消失
request，继续请求
session，登陆了就给一个对象，当前登陆就new这一个
application，基于应用程序的，一旦服务启动，就只new出来这么一个对象，之后一直就是它了