package com.lisz.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lisz.spring.entity.User;
import com.lisz.spring.service.MainService;

/**
 * 负责逻辑跳转
 * 在web环境之下线接入的就是Controller这一层
 */
@Controller("mainController")
public class MainController {
	
	@Autowired
	private MainService service;
	
	public String list() {
		String username = "zhangsan";
		String password = "123";
		User user = service.login(username, password);
		if (user == null) {
			return "Login failed";
		} else {
			return "Login successfully!";
		}
	}
}
