package com.lisz.spring.aop;

public class Test {

	public static void main(String[] args) {
		Human human = new GirlProxy(new Girl());//代理类作装饰
		human.eat();
	}

}
