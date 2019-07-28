package com.lisz.spring.hw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		A a = context.getBean("a", A.class);
		B b = context.getBean("b", B.class);
		C c = context.getBean("c", C.class);
		
		System.out.println(a.getB());
		System.out.println(b.getC());
		System.out.println(c.getA());
	}

}
