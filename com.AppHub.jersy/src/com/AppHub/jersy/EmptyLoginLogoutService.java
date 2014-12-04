package com.AppHub.jersy;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/EmptyLoginLogoutService")
public class EmptyLoginLogoutService {
	// Used for MVP for users to login and logout using BASIC authentication
	// headers

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(){
		return Response.ok().build();
	}
	
	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout() {
		return Response.status(401).build();
	}
	
}
