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

@Path("/PlatformService")
public class PlatformService {
	
	private PlatformTable platformTable = PlatformTable.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Platform> getAllPlatforms() {
		ArrayList<Platform> platforms = platformTable.getPlatformList();
		return platforms;
	}
	
	@GET
	@Path("/{idPlatform}")
	@Produces(MediaType.APPLICATION_JSON)
	public Platform getPlatform(@PathParam("idPlatform") String idPlatform) {
		Platform platform = platformTable.searchTable("idPlatforms", idPlatform);
		return platform;
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Platform createPlatform(Platform platform) {
		platform = platformTable.newPlatform(platform);
		return platform;
	}
	
	  
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Platform updatePlatform(Platform platform) {
		platform = platformTable.updatePlatform(platform);
		return platform;
	}
	
	@DELETE
	@Path("/remove/{idPlatform}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePlatform(@PathParam("idPlatform") String idPlatform) {
		boolean deleted = platformTable.deletePlatform(idPlatform);
		if(deleted) {
			return Response.status(201).entity("Platform " + idPlatform + " deleted").build();
		}
		else {
			return Response.status(400).entity("Platform " + idPlatform + " was not deleted").build();
		}
		  
	  }

	
}
