package com.lisz.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lisz.spring.dao.UserDao;
import com.lisz.spring.entity.User;

/**
 * 负责处理旧题业务逻辑，比如：
 * 校验账号密码是否正确
 * @author shuzheng
 *
 */
@Service //注解，按类型装配, 也不需要写set方法
public class MainService {
	@Autowired
	@Qualifier("mySQLUserDaoImpl")
	private UserDao dao;
	
	public User login(String username, String password) {
		System.out.println("Username: " + username + "  Password: " + password);
		System.out.println("Service received request and processing...");
		return dao.getUserByUsername(username);
	}
	
}
