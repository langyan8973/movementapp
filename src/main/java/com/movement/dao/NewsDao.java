package com.movement.dao;

import java.util.List;

import com.movement.bussiness.News;
import com.movement.bussiness.SportsEvent;


public interface NewsDao extends GenericDao<News, Integer> {

	public List<News> getNewsByEvent(SportsEvent event);
	
}
