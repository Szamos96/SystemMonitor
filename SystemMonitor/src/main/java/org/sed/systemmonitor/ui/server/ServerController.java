package org.sed.systemmonitor.ui.server;

public class ServerController {
	
	int packets = 0;
	
	private static ServerController serverController = null;
	private ServerView serverView;
	
	private ServerController() {
		
	}
	
	public void getUpload() {
		packets++;
		serverView.uploadHappened();
		
	}

	public static ServerController getInstance() {
		if (serverController == null) {
			serverController = new ServerController();
		}
		return serverController;
	}
	
	public void init() {
		serverView = new ServerView(this);
		serverView.setVisible(true);
	}
	
	
	
	
	
	
	

}
