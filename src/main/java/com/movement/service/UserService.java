package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.User;
import com.movement.bussiness.UserEvent;
import com.movement.dao.SportsEventDao;
import com.movement.dao.UserDao;
import com.movement.dao.UserEventDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private SportsEventDao sportsEventDao;
	
	@Autowired
	private UserEventDao userEventDao;
	
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
	
	public UserEvent reputably(Integer uid,Integer eid){
		
		User user = dao.findById(uid);
		
		SportsEvent event = sportsEventDao.findById(eid);
		
		UserEvent userEvent = userEventDao.getByUserAndEvent(user, event);
		
		userEvent.setReputably(userEvent.getReputably()+1);
		
		userEventDao.saveOrUpdate(userEvent);
		
		return userEvent;
		
	}
	
	public List<UserEvent> getUserEventsByUid(int uid){
		
		User user = dao.findById(uid);
		
		List<UserEvent> userEvents = userEventDao.getByUser(user);
		
		return userEvents;
		
	}
}
