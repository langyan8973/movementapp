package com.movement.dao;

import com.movement.bussiness.User;

public interface UserDao extends GenericDao<User, Integer> {

	public User findByOpenID(String openid, Integer snstype);
	
	public User findByUidSnsType(Integer uid, Integer snstype);
	
}
