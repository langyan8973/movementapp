package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Competition;
import com.movement.bussiness.Site;
import com.movement.service.CompetitionService;

@Component
public class SiteResource {

	private Site site;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	@Autowired
	private CompetitionService competitionService;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public Site getSingle(){
		
		return site;
		
	}
	
	@GET
	@Path("/competitions")
	@Produces("application/json;charset=UTF-8")
	public List<Competition> getCompetitions(){
		
		return competitionService.getBySite(site);
		
	}
}
