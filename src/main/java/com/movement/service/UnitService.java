package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.dao.UnitDao;
import com.movement.bussiness.Unit;

@Service
@Transactional
public class UnitService {

	@Autowired
	private UnitDao dao;
	
	public List<Unit> getAll(){
		
		List<Unit> units = dao.findAll();
		
		return units;
		
	}
	
	public Unit getById(int id){
		
		Unit unit = dao.findById(id);
		
		return unit;
		
	}
	
}
