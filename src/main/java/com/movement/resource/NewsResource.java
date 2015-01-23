package com.movement.resource;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.News;
import com.movement.bussiness.SportsEvent;
import com.movement.service.NewsService;


@Component
public class NewsResource {
	
	private SportsEvent event;
	
	public SportsEvent getEvent() {
		return event;
	}

	public void setEvent(SportsEvent event) {
		this.event = event;
	}

	@Autowired
	private NewsService newsService;

	@GET
	@Produces("application/json;charset=UTF-8")
	public List<News> getNewsByEvent() {
		List<News> newss = newsService.getByEvent(event);
		return newss;
	}
}
