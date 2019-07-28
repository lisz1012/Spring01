package com.lisz.spring.hw;

public class A {
	private B b;
	public A() {
		System.out.println("A init");
	}
	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
	
}
