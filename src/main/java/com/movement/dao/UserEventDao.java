package com.movement.dao;

import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.User;
import com.movement.bussiness.UserEvent;

public interface UserEventDao extends GenericDao<UserEvent, Integer> {
	
	public UserEvent getByUserAndEvent(User user,SportsEvent event);

}
