package com.lisz.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lisz.spring.controller.MainController;

public class TestGetBean {

	public static void main(String[] args) {
		// Web项目会交给tomcat等容器初始化
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");//可以写多个参数, "applicationContext-service.xml");
		
		MainController controller = context.getBean("mainController", MainController.class);
		System.out.println(controller.list());
	}

}
