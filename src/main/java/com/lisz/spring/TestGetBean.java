package com.lisz.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestGetBean {

	public static void main(String[] args) {
		// Web项目会交给tomcat等容器初始化
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");//可以写多个参数, "applicationContext-service.xml");
		Person p = context.getBean(Person.class);
		Person p2 = context.getBean("person", Person.class);
		Person p3 = context.getBean("human", Person.class);
		Person p4 = context.getBean("human", Person.class);
		Person p5 = context.getBean("person2", Person.class);
		System.out.println(p2 == p3 && p3 == p4 && p4 == p5); // 默认singleton，所以true；但是若Person的bean标签里有scope="prototype"则为false
		/*Food f = context.getBean(Food.class);
		System.out.println(p);
		p.setName("Zhangsan");
		p.setAge(18);
		p.setFood(f);
		System.out.println(p.getName() + " - " + p.getAge());
		System.out.println(context.getBean(Person.class));//每次返回同一个对象，单例*/
		System.out.println(ToStringBuilder.reflectionToString(p, ToStringStyle.JSON_STYLE));
		System.out.println(ToStringBuilder.reflectionToString(context, ToStringStyle.MULTI_LINE_STYLE));
	}

}
