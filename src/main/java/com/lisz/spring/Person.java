package com.lisz.spring;

import java.util.Properties;

public class Person {
	private String name;
	private int age;
	private Food food;
	private Properties skill;
	
	public Properties getSkill() {
		return skill;
	}
	public void setSkill(Properties skill) {
		this.skill = skill;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
