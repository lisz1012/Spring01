package com.lisz.spring.aop;

public class Test {

	public static void main(String[] args) {
		Girl girl = new Girl();
		CGLibFactory factory = new CGLibFactory(girl);	
		Girl proxy = (Girl)factory.createProxy();
		//System.out.println("Proxy : " + proxy); //这里打印还会报警告：An illegal reflective access operation has occurred
		//System.out.println("Source: " + girl);
		proxy.eat();
	}

}
