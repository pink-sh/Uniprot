package org.abcam.test.uniprot.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.uniprot.uniprot.Uniprot;

public class TestJaxB {

	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext jc = JAXBContext.newInstance("org.uniprot.uniprot");

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        URL url = new URL("http://www.uniprot.org/uniprot/P12345.xml");
        InputStream xml = url.openStream();
        JAXBElement<Uniprot> feed = unmarshaller.unmarshal(new StreamSource(xml), Uniprot.class);
        xml.close();

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(feed, System.out);

	}

}
