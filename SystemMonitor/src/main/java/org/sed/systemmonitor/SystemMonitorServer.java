package org.sed.systemmonitor;

import org.sed.systemmonitor.ui.server.ServerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SystemMonitorServer {

	public void startServer() {
		SpringApplication.run(SystemMonitorServer.class);
		
		ServerController.getInstance().init();
	}

}
