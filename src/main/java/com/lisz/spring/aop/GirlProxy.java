package com.lisz.spring.aop;


public class GirlProxy implements Human {
	private Human human;
	
	public GirlProxy(Human human) {
		this.human = human;
	}

	public void eat() {
		System.out.println("洗手");
		human.eat();
		System.out.println("漱口");
	}

}
