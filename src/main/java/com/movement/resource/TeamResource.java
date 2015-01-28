package com.movement.resource;

import java.io.InputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Team;
import com.movement.bussiness.UserTeam;
import com.movement.service.FileService;
import com.movement.service.TeamService;
import com.sun.jersey.multipart.FormDataParam;

@Component
public class TeamResource {

	private Team team;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Autowired
	private TeamService service;
	
	@Autowired 
	private FileService fileService;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public Team getSingle(){
		
		return team;
		
	}
	
	@POST
	@Path("/join")
	@Consumes("multipart/form-data")
	public Response create(@FormDataParam("uid") Integer uid){
		
		service.joinTeam(uid, team);
		
		return Response.created(URI.create(String.valueOf(team.getId()))).build();
		
	}
	
	@GET
	@Path("/members")
	@Produces("application/json;charset=UTF-8")
	public List<UserTeam> getMembers(){
		
		return service.getAllMembers(team);
		
	}
	
	@GET
	@Path("/checkedmembers")
	@Produces("application/json;charset=UTF-8")
	public List<UserTeam> getCheckedMembers(){
		
		return service.getCheckedMembers(team);
		
	}
	
	@PUT
	@Path("/members/{utid}/check")
	@Produces("application/json;charset=UTF-8")
	public Response checkUserTeam(@PathParam("utid") int utid){
		
		UserTeam userTeam = service.checkPeople(utid, 1);
		
		return Response.created(URI.create(String.valueOf(userTeam.getId()))).build();
		
	}
	
	@PUT
	@Path("/members/{utid}/uncheck")
	@Produces("application/json;charset=UTF-8")
	public Response uncheckUserTeam(@PathParam("utid") int utid){
		
		UserTeam userTeam = service.checkPeople(utid, -1);
		
		return Response.created(URI.create(String.valueOf(userTeam.getId()))).build();
		
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
			
			String fname = fileService.saveImage(filename,"team",team.getId().toString(), is);
			
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
		
		fileService.deleteImage(name, "team", team.getId().toString());
		
		return Response.ok().build();
		
	}
}
