package com.AppHub.jersy;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import database_console.*;
import model.*;

@Path("/AppCommentsService")
public class AppCommentsService {

	private AppCommentsTable appCommentsTable = AppCommentsTable.getInstance();
	  
	  @GET
	  @Path("/{appId}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<AppComments> getAppComments(@PathParam("appId") String appId) {
		  ArrayList<AppComments> appComments = appCommentsTable.getAppCommentsForApp(appId);
		  return appComments;
	  }
	  
	  @POST
	  @Path("/create")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public AppComments createAppComment(AppComments appComment) {
		  appComment = appCommentsTable.newComment(appComment);
		  return appComment;
	  }
	  
	  @PUT
	  @Path("/update")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public AppComments updateAppComment(AppComments appComment) {
		  appComment = appCommentsTable.updateComment(appComment);
		  return appComment;
	  }
	  
	  @DELETE
	  @Path("/remove/{commentId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response deleteApp(@PathParam("commentId") String commentId) {
		  boolean deleted = appCommentsTable.deleteComment(commentId);
		  if(deleted) {
			  return Response.status(201).entity("Comment " + commentId + " deleted").build();
		  }
		  else {
			  return Response.status(400).entity("Comment " + commentId + " was not deleted").build();
		  }
		  
	  }

}
