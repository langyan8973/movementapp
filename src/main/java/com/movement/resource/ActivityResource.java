package com.movement.resource;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
	
	@POST
	@Path("/addimage")
	@Consumes("multipart/form-data")
	public Response addImg(@Context HttpServletRequest request){
		
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		
		try {
			
			List<FileItem> fileItems = upload.parseRequest(request);
			
			String filename = "";
			
			InputStream is = null;
			
			Iterator<FileItem> iterator;
			
			for (iterator = fileItems.iterator();iterator.hasNext();) {
				
				FileItem item = iterator.next();
				
				if(item.isFormField()&&item.getFieldName().equals("filename")){
					
					filename = item.getString("UTF-8");
					
				}
				else if(item.getName() != null && !item.getName().equals("")){
					
					is = item.getInputStream();
					
					
				}
				
			}
			
			String fname = fileService.saveImage(filename,"activity",activity.getId().toString(), is);
			
			is.close();
			
			return Response.created(URI.create(fname))
					.build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		
	}
	
	@POST
	@Path("/deleteimage")
	@Consumes("multipart/form-data")
	public Response deleteImg(@FormDataParam("name") String name){
		
		fileService.deleteImage(name, "activity", activity.getId().toString());
		
		return Response.ok().build();
		
	}
}
