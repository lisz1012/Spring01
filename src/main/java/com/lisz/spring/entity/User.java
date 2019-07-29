package com.lisz.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ORM映射，要求对象保证线程安全
 * 这里面有很多属性，成员属性不能被共享
 * @author shuzheng
 *
 */
@Component
@Scope("prototype")
public class User {
	@Value("Zhangsan")
	private String username;
	@Value("123")
	private String password;
	@Value("12.3")
	private double money;
	@Autowired
	private Pet pet;
}
