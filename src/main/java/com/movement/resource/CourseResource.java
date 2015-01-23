package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Course;
import com.movement.bussiness.SportsEvent;
import com.movement.service.CourseService;

@Component
public class CourseResource {
	
	private SportsEvent event;

	public SportsEvent getEvent() {
		return event;
	}

	public void setEvent(SportsEvent event) {
		this.event = event;
	}

	@Autowired
	private CourseService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<Course> getByEvent(){
		
		return service.getByEvent(event);
		
	}
}
