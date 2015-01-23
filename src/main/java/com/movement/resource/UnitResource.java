package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Competition;
import com.movement.bussiness.Unit;
import com.movement.service.CompetitionService;

@Component
public class UnitResource {
	
	private Unit unit;

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	@Autowired
	private CompetitionService competitionService;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public Unit getSingle(){
		
		return unit;
		
	}
	
	@GET
	@Path("/competitions")
	@Produces("application/json;charset=UTF-8")
	public List<Competition> getCompetitions(){
		
		return competitionService.getByUnit(unit);
		
	}

}
