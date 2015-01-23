package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Site;
import com.movement.service.SiteService;
import com.sun.jersey.api.core.ResourceContext;

@Component
@Path("/sites")
public class SitesResource {
	
	@Context
	private ResourceContext resourceContext;
	
	@Autowired
	private SiteService service;
	
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<Site> getAll(){
		
		return service.getAll();
		
	}
	
	@Path("{id}")
	public SiteResource getSite(@PathParam("id") int id){
		
		Site site = service.getById(id);
		
		SiteResource siteResource = resourceContext.getResource(SiteResource.class);
		
		siteResource.setSite(site);
		
		return siteResource;
		
	}

}
