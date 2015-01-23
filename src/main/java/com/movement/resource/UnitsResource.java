package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Unit;
import com.movement.service.UnitService;
import com.sun.jersey.api.core.ResourceContext;

@Component
@Path("/units")
public class UnitsResource {
	
	@Context
	private ResourceContext resourceContext;

	@Autowired
	private UnitService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<Unit> getAll(){
		
		return service.getAll();
		
	}
	
	@Path("{id}")
	public UnitResource getUnit(@PathParam("id") int id){
		
		Unit unit = service.getById(id);
		
		UnitResource unitResource = resourceContext.getResource(UnitResource.class);
		
		unitResource.setUnit(unit);
		
		return unitResource;
		
	}
	
}
