package com.movement.resource;

import java.io.InputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.bussiness.Game;
import com.movement.service.CompetitionService;
import com.movement.service.FileService;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.multipart.FormDataParam;

@Component
public class CompetitionResource {
	
	private Competition competition;

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	@Context
	private ResourceContext resourceContext;

	@Autowired
	private CompetitionService service;
	
	@Autowired
	private FileService fileService;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public Competition getSingle(){
		
		return competition;
		
	}
	
	@Path("/competitionteams")
	public CompetitionTeamsResource getTeams(){
		
		CompetitionTeamsResource resource = resourceContext.getResource(CompetitionTeamsResource.class);
		
		resource.setCompetition(competition);
		
		return resource;
		
	}
	
	@Path("/players")
	public CompetitionUsersResource getPlayers(){
		
		CompetitionUsersResource resource = resourceContext.getResource(CompetitionUsersResource.class);
		
		resource.setCompetition(competition);
		
		return resource;
		
	}
	
	@POST
	@Path("/edit")
	@Consumes("application/json")
	public Response edit(Competition competition1){
		
		competition.setAddress(competition1.getAddress());
		
		competition.setDescription(competition1.getDescription());
		
		competition.setEvent(competition1.getEvent());
		
		competition.setSite(competition1.getSite());
		
		competition.setSponsor(competition1.getSponsor());
		
		competition.setStatus(competition1.getStatus());
		
		competition.setTime(competition1.getTime());
		
		competition.setTitle(competition1.getTitle());
		
		service.saveOrUpdate(competition);
		
		return Response.created(URI.create(String.valueOf(competition.getId()))).build();
		
	}
	
	@POST
	@Path("/join")
	@Consumes("application/x-www-form-urlencoded")
	public Response join(@FormParam("tid") Integer tid,@Context UriInfo uriInfo,
			@Context HttpServletRequest request,
			@Context SecurityContext securityContext){
		
		service.JoinCompetition(tid, competition);
		
		return Response.created(URI.create(String.valueOf(competition.getId()))).build();
		
	}
	
	@POST
	@Path("/playerjoin")
	@Consumes("application/x-www-form-urlencoded")
	public Response playerjoin(@FormParam("uid") Integer uid,@Context UriInfo uriInfo,
			@Context HttpServletRequest request,
			@Context SecurityContext securityContext){
		
		service.playerJoin(uid, competition);
		
		return Response.created(URI.create(String.valueOf(competition.getId()))).build();
		
	}
	
	@POST
	@Path("/addgame")
	@Consumes("application/json")
	public Response addGame(Game game){
		
		game.setCompetition(competition);
		
		game.setStatus(0);
		
		service.saveOrUpdateGame(game);
		
		return Response.created(URI.create(String.valueOf(game.getId()))).build();
		
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
			
			String fname = fileService.saveImage(filename,"competition",competition.getId().toString(), is);
			
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
	@Consumes("application/x-www-form-urlencoded")
	public Response deleteImg(@FormParam("name") String name,@Context UriInfo uriInfo,
			@Context HttpServletRequest request,
			@Context SecurityContext securityContext){
		
		fileService.deleteImage(name, "competition", competition.getId().toString());
		
		return Response.ok().build();
		
	}
	
	@PUT
	@Path("/close")
	public Response close(){
		
		service.closeCompetition(competition);
		
		return Response.ok().build();
		
	}
	
	
}
