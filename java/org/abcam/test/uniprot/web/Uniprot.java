package org.abcam.test.uniprot.web;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("")
public class Uniprot {
	
	@Context ServletContext context;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("test/")
	public String test() {
		return "Here we are!";
	}
}
