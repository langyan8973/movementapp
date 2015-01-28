package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movement.bussiness.User;
import com.movement.dao.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao dao;
	
	public List<User> getAllUsers(){
		
		List<User> users = dao.findAll();
		
		return users;
	}
	
	public User getByOpenid(String openid, Integer snstype){
		return dao.findByOpenID(openid, snstype);
	}
	
	public User getById(String id){
		
		int iid = Integer.parseInt(id);
		
		return dao.findById(iid);
		
	}
	
	public User create(String openid, String name,String thumbnail, String accessToken, String refreshToken, long expirein, Integer snstype) {
		User user = new User();
		user.setName(name);
		user.setThumbnail(thumbnail);
		user.setSns_type(snstype);
		user.setOpenid(openid);
		user.setAccess_token(accessToken);
		user.setRefreshtoken(refreshToken);
		user.setExpires_in(new java.util.Date(System.currentTimeMillis() + (long)expirein*1000));
		dao.saveOrUpdate(user);
		
		return user;
	}
	
	
	public void update(String name,String thumbnail, String accessToken, String refreshToken, long expirein,User user){
		user.setName(name);
		user.setThumbnail(thumbnail);
		user.setAccess_token(accessToken);
		user.setRefreshtoken(refreshToken);
		user.setExpires_in(new java.util.Date(System.currentTimeMillis() + (long)expirein*1000));
		dao.saveOrUpdate(user);
	}
}
