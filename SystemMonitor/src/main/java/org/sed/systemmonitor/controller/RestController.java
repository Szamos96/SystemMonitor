package org.sed.systemmonitor.controller;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.sed.systemmonitor.client.SystemInformationSender;
import org.sed.systemmonitor.model.SystemInformation;
import org.sed.systemmonitor.repository.SystemInformationService;
import org.sed.systemmonitor.ui.server.ServerController;
import org.sed.systemmonitor.util.SystemInformationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/rest")
public class RestController {

	@Autowired
	SystemInformationService systemInformationService;

	@GetMapping("/getSystemInformations")
	public List<SystemInformation> getSystemInformation() {

		return systemInformationService.findAll();
	}

	@GetMapping("/test")
	public boolean databaseAndServerTest() {

		try {
			systemInformationService.findAll();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	@GetMapping("/getBetweenDates")
	public List<SystemInformation> getBetweenDates() {
	
		return systemInformationService.findAll();
		
	}

	@RequestMapping(path = "/addSystemInformation", consumes = { "application/json" }, method = RequestMethod.POST)
	public @ResponseBody SystemInformation addSystemInformation(@RequestBody SystemInformation systemInformation) {

		systemInformationService.save(systemInformation);
		ServerController.getInstance().getUpload();
		return systemInformation;
	}
}
