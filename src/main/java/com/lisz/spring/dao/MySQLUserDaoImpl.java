package com.lisz.spring.dao;

import org.springframework.stereotype.Repository;

import com.lisz.spring.entity.User;

@Repository("mySQLUserDaoImpl") //注解，按类型装配, 也不需要写set方法
public class MySQLUserDaoImpl implements UserDao {

	public User getUserByUsername(String username) {
		System.out.println("MySQL Username : "  + username);
		return new User();
	}
	
}
