package com.lisz.spring;

public class PlaneFactory {
	public static Plane getInstance(String name) {
		if (name.equalsIgnoreCase("F22")) {
			return new F22();
		} else {
			throw new IllegalArgumentException("现在还不能造这款飞机");
		}
	}
}
