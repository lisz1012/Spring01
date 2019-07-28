package com.lisz.spring.hw;

public class B {
	private C c;
	public B() {
		System.out.println("B init");
	}
	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}
}
