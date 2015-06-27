package org.abcam.test.uniprot.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.uniprot.uniprot.Uniprot;

@Path("")
public class UniprotRest {
	
	@Context ServletContext context;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("test/")
	public String test() {
		return "Here we are!";
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getProtein/{id}")
	public Response getProtein(@PathParam("id") String id) {
		
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance("org.uniprot.uniprot");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	        URL url = new URL("http://www.uniprot.org/uniprot/" + id.toUpperCase() + ".xml");
	        InputStream xml = url.openStream();
	        JAXBElement<Uniprot> feed = unmarshaller.unmarshal(new StreamSource(xml), Uniprot.class);
	        xml.close();

	        /*Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(feed, System.out);*/
	        
	        ObjectMapper mapper = new ObjectMapper();
	        return Response.ok(mapper.writeValueAsString(feed)).build();
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.status(500).build();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return Response.status(500).build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
}
