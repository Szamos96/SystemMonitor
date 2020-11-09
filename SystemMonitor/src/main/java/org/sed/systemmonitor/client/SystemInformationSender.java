package org.sed.systemmonitor.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.sed.systemmonitor.model.SystemInformation;
import org.sed.systemmonitor.util.SystemInformationFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SystemInformationSender {
	
	
	public static void send() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:"+8080+"/rest/addSystemInformation/";
	    URI uri = new URI(baseUrl);
	     
	    SystemInformation systemInfo = SystemInformationFactory.getInstance().createSystemInformation();
	 
	    ResponseEntity<String> result = restTemplate.postForEntity(uri, systemInfo, String.class);
	    
	}
	
	
	

}
