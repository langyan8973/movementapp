package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Activity;
import com.movement.bussiness.User;
import com.movement.bussiness.UserActivity;

public interface UserActivityDao extends GenericDao<UserActivity, Integer> {

	public UserActivity getByUserAndActivity(User user,Activity activity);
	
	public List<UserActivity> getByActivity(Activity activity);
	
}
