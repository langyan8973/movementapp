package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movement.bussiness.News;
import com.movement.bussiness.SportsEvent;
import com.movement.dao.NewsDao;
import com.movement.daoimp.NewsDaoImp;

@Service
@Transactional
public class NewsService {
	
	@Autowired
	private NewsDao dao;
	
	public List<News> getAllNews(){
		
		return dao.findAll();
		
	}
	
	public List<News> getByEvent(SportsEvent event){
		List<News> news = dao.getNewsByEvent(event);
		return news;
	}

}
