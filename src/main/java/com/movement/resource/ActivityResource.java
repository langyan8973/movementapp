package com.movement.resource;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Activity;
import com.movement.bussiness.UserActivity;
import com.movement.service.ActivityService;
import com.movement.service.FileService;
import com.sun.jersey.multipart.FormDataParam;

@Component
public class ActivityResource {

	private Activity activity;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
	@Autowired
	private ActivityService service;
	
	@Autowired
	private FileService fileService;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public Activity getSingle(){
		
		return activity;
		
	}
	
	@POST
	@Path("/join")
	@Consumes("multipart/form-data")
	public Response join(@FormDataParam("uid") Integer uid){
		
		service.JoinActivity(uid, activity);
		
		return Response.created(URI.create(String.valueOf(activity.getId()))).build();
		
	}
	
	@POST
	@Path("/edit")
	@Consumes("application/json")
	public Response edit(Activity ac){
		
		activity.setAddress(ac.getAddress());
		
		activity.setAttachments(ac.getAttachments());
		
		activity.setContent(ac.getContent());
		
		activity.setEvent(ac.getEvent());
		
		activity.setConstitutor(ac.getConstitutor());
		
		activity.setTime(ac.getTime());
		
		activity.setTitle(ac.getTitle());
		
		service.edit(activity);
		
		return Response.created(URI.create(String.valueOf(activity.getId()))).build();
		
	}
	
	@GET
	@Path("/peoples")
	@Produces("application/json;charset=UTF-8")
	public List<UserActivity> getAllPeoples(){
		
		return service.getAllPeoples(activity);
		
	}
	
	@POST
	@Path("/addattachment")
	@Consumes("multipart/form-data")
	public Response addImage(@FormDataParam("name") String name,
			@FormDataParam("image") InputStream upImg){
		
		String filename = fileService.saveImage(name,"activity",activity.getId().toString(), upImg);
		return Response.created(URI.create(filename))
				.build();
		
		
	}
}
