package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.Course;
import com.movement.bussiness.SportsEvent;
import com.movement.dao.CourseDao;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CourseDao dao;
	
	public List<Course> getAllCourses(){
		
		List<Course> courses = dao.findAll();
		
		return courses;
		
	}
	
	public List<Course> getByEvent(SportsEvent event){
		
		List<Course> courses = dao.findByEvent(event);
		
		return courses;
	}
}
