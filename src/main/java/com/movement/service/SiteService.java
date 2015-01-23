package com.movement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.bussiness.Site;
import com.movement.dao.SiteDao;

@Service
@Transactional
public class SiteService {

	@Autowired
	private SiteDao dao;
	
	public List<Site> getAll(){
		
		List<Site> sites = dao.findAll();
		
		return sites;
	}
	
	public Site getById(int id){
		
		Site site = dao.findById(id);
		
		return site;
		
	}
	
}
