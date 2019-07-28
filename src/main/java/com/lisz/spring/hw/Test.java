package com.lisz.spring.hw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		B b = context.getBean("b", B.class); //lazy-init，先用到了b所以先初始化b，由于有C的强引用注入，所以又去初始化C，C又去初始化A，此时懒加载就拦不住了，还是要提前准备好。不得不new的时候提前了
		System.out.println(b);
		A a = context.getBean("a", A.class);
		System.out.println(a);
		C c = context.getBean("c", C.class);
		System.out.println(c);
		
		
		/*
B init
C init
A init
com.lisz.spring.hw.B@24fcf36f
com.lisz.spring.hw.A@10feca44
com.lisz.spring.hw.C@3fb1549b
		 */
	}

}
