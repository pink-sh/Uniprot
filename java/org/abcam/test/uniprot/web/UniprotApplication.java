package org.abcam.test.uniprot.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

public class UniprotApplication {
	@ApplicationPath("/")
	public class CGRFAApplication extends ResourceConfig {
		public CGRFAApplication() {
			register(new Uniprot());
		}
	}
}
