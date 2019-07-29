package com.lisz.spring.aop;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibFactory implements MethodInterceptor {
	private Object target;
	
	public CGLibFactory() {}
	
	public CGLibFactory(Object target) {
		this.target = target;
	}
	
	public Object createProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("Before ... ");
		Object ret = proxy.invokeSuper(obj, args); // method.invoke(target, args);也可以
		System.out.println("After ... ");
		return ret;
	}
	
}
