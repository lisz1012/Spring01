package com.lisz.spring.hw;

import org.apache.commons.lang3.builder.ToStringBuilder;
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
		
		System.out.println(ToStringBuilder.reflectionToString(a));
		System.out.println(ToStringBuilder.reflectionToString(a.getB()));
		System.out.println(ToStringBuilder.reflectionToString(a.getB().getC()));
		
		/*
com.lisz.spring.hw.B@24fcf36f
com.lisz.spring.hw.C@10feca44
com.lisz.spring.hw.A@3fb1549b
com.lisz.spring.hw.A@3fb1549b[b=com.lisz.spring.hw.B@24fcf36f]
com.lisz.spring.hw.B@24fcf36f[c=com.lisz.spring.hw.C@10feca44]
com.lisz.spring.hw.C@10feca44[a=com.lisz.spring.hw.A@3fb1549b]
		 */
	}

}
