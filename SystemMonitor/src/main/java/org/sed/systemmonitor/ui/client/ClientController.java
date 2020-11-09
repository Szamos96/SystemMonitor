package org.sed.systemmonitor.ui.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.sed.systemmonitor.model.SystemInformation;
import org.sed.systemmonitor.util.SystemInformationFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ClientController {

	String ip = "http://localhost";
	int port = 8080;
	int freq = 60;
	
	ClientView clientView;

	private static ClientController clientController = null;

	private ClientController() {

	}

	public static ClientController getInstance() {
		if (clientController == null) {
			clientController = new ClientController();
		}
		return clientController;
	}

	public void init() {
		clientView = new ClientView(this);
		clientView.setVisible(true);
	}
	
	public List<SystemInformation> getBetweenDates(Date end, Date start) throws URISyntaxException{
		
		
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = ip + ":" + port + "/rest/getBetweenDates/";
		URI uri = new URI(baseUrl);
		
		ResponseEntity<SystemInformation[]> result = restTemplate.getForEntity(uri, SystemInformation[].class);
		
		List<SystemInformation> collect = Arrays.asList(result.getBody()).stream()
				.filter( systemInfo -> systemInfo.getTimestamp().before(end) && systemInfo.getTimestamp().after(start))
				.collect(Collectors.toList());
			
		return collect;
		
	}
	
	public void start() {
		TimerTask task = new TimerTask()
		{
		    int minutes = freq;
		    int i = 0;
		    @Override
		    public void run()
		    {
		       i++;

		       if(i % minutes == 0)
				try {
					send();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			else
		    	   clientView.remainField.setText((minutes - (i %minutes))+"");
		    }
		};

		Timer timer = new Timer();
		timer.schedule(task, 0, 60000);
	}

	public boolean test() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = ip + ":" + port + "/rest/test/";
		URI uri = new URI(baseUrl);
		ResponseEntity<Boolean> result = restTemplate.getForEntity(baseUrl, Boolean.class);
		return result.getBody().booleanValue();
	}

	public void save(String ip, int port, int freq) {
		this.ip = ip;
		this.port = port;
		this.freq = freq;
	}

	public void send() throws URISyntaxException {
		
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = ip + ":" + port + "/rest/addSystemInformation/";
		URI uri = new URI(baseUrl);
		SystemInformation systemInfo = SystemInformationFactory.getInstance().createSystemInformation();
		ResponseEntity<String> result = restTemplate.postForEntity(uri, systemInfo, String.class);

	}

}
