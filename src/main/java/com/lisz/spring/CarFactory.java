package com.lisz.spring;

public class CarFactory {
	private Car audi = new Audi();
	private Car BMW = new BMW();
	public Car getInstance(String name) {
		if (name.equalsIgnoreCase("audi")) {
			return audi;
		} else {
			return BMW;
		}
	}
}
