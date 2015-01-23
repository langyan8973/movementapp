package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.SportsEvent;
import com.movement.dao.SportsEventDao;

@Service
@Transactional
public class SportsEventService {

	@Autowired
	private SportsEventDao dao;
	
	public List<SportsEvent> getAllEvents(){
		
		List<SportsEvent> events = dao.findAll();
		
		return events;
		
	}
	
	public SportsEvent getEventByid(int id){

		SportsEvent event = dao.findById(id);
		
		return event;
		
	}
	
}
