package com.lisz.spring.hw;

public class C {
	private A a;
	public C() {
		System.out.println("C init");
	}
	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
