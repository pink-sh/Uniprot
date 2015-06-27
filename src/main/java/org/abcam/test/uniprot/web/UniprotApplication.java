package org.abcam.test.uniprot.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest/")
public class UniprotApplication extends ResourceConfig {
	public UniprotApplication() {
		register(new UniprotRest());
	}
}

