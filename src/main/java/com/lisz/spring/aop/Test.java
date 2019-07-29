package com.lisz.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		final Human girl = new Girl();
		
		Human human = (Human)Proxy.newProxyInstance(Girl.class.getClassLoader(), Girl.class.getInterfaces(), new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("Before");
				Object retVal = method.invoke(girl, args);
				System.out.println("After");
				return retVal;
			}
		});
		human.eat();
	}

}
