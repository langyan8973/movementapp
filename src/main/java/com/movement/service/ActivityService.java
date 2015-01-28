package com.movement.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.Activity;
import com.movement.bussiness.ActivityAttachment;
import com.movement.bussiness.EventLevel;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.User;
import com.movement.bussiness.UserActivity;
import com.movement.bussiness.UserEvent;
import com.movement.dao.ActivityAttachmentDao;
import com.movement.dao.ActivityDao;
import com.movement.dao.EventLevelDao;
import com.movement.dao.UserActivityDao;
import com.movement.dao.UserDao;
import com.movement.dao.UserEventDao;
import com.movement.util.CodeUpgradeType;
import com.movement.util.JaxbDateSerializer;

@Service
@Transactional
public class ActivityService {

	@Autowired
	private ActivityDao dao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserEventDao userEventDao;
	
	@Autowired
	private UserActivityDao userActivityDao;
	
	@Autowired
	private ActivityAttachmentDao activityAttachmentDao;
	
	@Autowired
	private EventLevelDao eventLevelDao;
	
	public List<Activity> getAllActivities(){
		
		List<Activity> activities = dao.findAll();
		
		return activities;
	}
	
	public List<Activity> getActivitiesByevent(SportsEvent event){
		List<Activity> activities = dao.getByEvent(event);
		
		return activities;
	}
	
	public Activity getById(int id){
		
		Activity activity = dao.findById(id);
		
		return activity;
		
	}
	
	public List<UserActivity> getAllPeoples(Activity activity){
		return userActivityDao.getByActivity(activity);
	}
	
	public Activity create(String title,String content,
			String time,String address,Integer uid,SportsEvent event){
		
		Activity activity = new Activity();
		
		activity.setTitle(title);
		
		activity.setContent(content);
		
		JaxbDateSerializer jds = new JaxbDateSerializer();
		
		try {
			Date dt = jds.unmarshal(time);
			activity.setTime(dt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		activity.setAddress(address);
		
		activity.setEvent(event);
		
		User user = userDao.findById(uid);
		
		activity.setInitiator(user);
		
		activity.setStatus(0);
		
		dao.saveOrUpdate(activity);
		
		UserEvent userEvent = userEventDao.getByUserAndEvent(user, event);
		
		if(userEvent==null){
			
			userEvent = new UserEvent();
			
			userEvent.setEvent(event);
			
			userEvent.setUser(user);
			
			EventLevel eventLevel = eventLevelDao.findById(1);
			
			userEvent.setLevel(eventLevel);
			
			userEvent.setStatus(0);
			
			userEventDao.saveOrUpdate(userEvent);
			
		}
		
		UserActivity userActivity = new UserActivity();
		
		userActivity.setActivity(activity);
		
		userActivity.setUser(user);
		
		userActivity.setStatus(1);
		
		userActivityDao.saveOrUpdate(userActivity);
		
		return activity;
		
	}
	
	public void JoinActivity(Integer uid,Activity activity){
		
		User user = userDao.findById(uid);
		
		UserEvent userEvent = userEventDao.getByUserAndEvent(user, activity.getEvent());
		
		if(userEvent==null){
			
			userEvent = new UserEvent();
			
			userEvent.setEvent(activity.getEvent());
			
			userEvent.setUser(user);
			
			EventLevel eventLevel = eventLevelDao.findById(1);
			
			userEvent.setLevel(eventLevel);
			
			userEvent.setStatus(0);
			
			userEventDao.saveOrUpdate(userEvent);
			
		}
		
		UserActivity userActivity = new UserActivity();
		
		userActivity.setActivity(activity);
		
		userActivity.setUser(user);
		
		userActivity.setStatus(0);
		
		userActivityDao.saveOrUpdate(userActivity);
		
	}
	
	public Activity edit(Activity activity){
		
		dao.saveOrUpdate(activity);
		
		List<ActivityAttachment> activityAttachments = activityAttachmentDao.getByActivity(activity);
		
		if(activityAttachments!=null && activityAttachments.size()>0){
			
			Iterator<ActivityAttachment> iterator;
			for (iterator = activityAttachments.iterator(); iterator
					.hasNext();){
				
				ActivityAttachment activityAttachment = iterator.next();
				activityAttachmentDao.delete(activityAttachment);
				
			}
		}
		
		if(activity.getAttachments()!=null && activity.getAttachments().size()>0){
			
			Iterator<ActivityAttachment> iterator;
			for (iterator = activity.getAttachments().iterator(); iterator
					.hasNext();) {
				ActivityAttachment activityAttachment = iterator.next();
				
				activityAttachment.setActivity(activity);
				
				activityAttachment.setId(null);
				
				activityAttachmentDao.saveOrUpdate(activityAttachment);
				
			}
			
		}
		
		return activity;
		
	}
	
	public void closeActivity(Activity activity){
		
		activity.setStatus(2);
		
		dao.saveOrUpdate(activity);
		
		List<UserActivity> userActivities = userActivityDao.getByActivity(activity);
		
		if(userActivities!=null && userActivities.size()>0){
			
			Iterator<UserActivity> iterator;
			for (iterator = userActivities.iterator(); iterator.hasNext();) {
				
				UserActivity userActivity = iterator.next();
				
				UserEvent userEvent = userEventDao.getByUserAndEvent(userActivity.getUser(), userActivity.getActivity().getEvent());
				
				userEvent.setExperiencer(userEvent.getExperiencer()+CodeUpgradeType.ACTIVITY_EXPERIENCER);
				
				int grade = userEvent.getExperiencer()/CodeUpgradeType.EVENT_UPGRADE_UNIT;
				
				userEvent.setGrade(grade);
				
				userEventDao.saveOrUpdate(userEvent);
				
			}
			
		}
		
	}
	
}
