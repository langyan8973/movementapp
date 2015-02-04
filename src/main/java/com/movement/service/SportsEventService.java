package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.UserEvent;
import com.movement.dao.SportsEventDao;
import com.movement.dao.UserEventDao;

@Service
@Transactional
public class SportsEventService {

	@Autowired
	private SportsEventDao dao;
	
	@Autowired
	private UserEventDao userEventDao;
	
	public List<SportsEvent> getAllEvents(){
		
		List<SportsEvent> events = dao.findAll();
		
		return events;
		
	}
	
	public SportsEvent getEventByid(int id){

		SportsEvent event = dao.findById(id);
		
		return event;
		
	}
	
	public List<UserEvent> getUserEventsByEvent(SportsEvent event){
		
		List<UserEvent> userEvents = userEventDao.getByEvents(event);
		
		return userEvents;
		
	}
	
}
