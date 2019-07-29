package com.lisz.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lisz.spring.entity.User;

/**
 * 一个线程创建连接
 * 另一个线程关闭连接
 * 对应的类：Connection
 * JDK用ThreadLocal隔离状态数据，所以Connection类本质上说还是无状态的，所以可以单例
 * @author shuzheng
 *
 */
@Repository("mySQLUserDaoImpl") //注解，按类型装配, 也不需要写set方法
public class MySQLUserDaoImpl implements UserDao {
	@Autowired
	private User user;
	
	public User getUserByUsername(String username) {
		System.out.println("MySQL Username : "  + username);
		return user;
	}
	
}
