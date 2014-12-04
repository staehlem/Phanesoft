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

@Path("/UserService")
public class UserService {
	private UserTable userTable = UserTable.getInstance();
		  
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = userTable.getAllUsers();
		return users;
	}
	  
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userId") String userId) {
		User user = userTable.searchTable("username", "'"+userId+"'");
		return user;
	}
	  
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User user) {
		user = userTable.newUser(user);
		return user;
	}
	  
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		user = userTable.updateUser(user);
		return user;
	}
	  
	@DELETE
	@Path("/remove/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("userId") String userId) {
		boolean deleted = userTable.deleteUser(userId);
		if(deleted) {
			return Response.status(201).entity("User " + userId + " deleted").build();
		}
		else {
			return Response.status(400).entity("User " + userId + " was not deleted").build();
		}
		  
	}

}
