package org.sed.systemmonitor;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sed.systemmonitor.model.SystemInformation;
import org.sed.systemmonitor.ui.client.ClientController;
import org.sed.systemmonitor.ui.client.ClientView;

public class SystemMonitorClient {

	public void startClient() {

		ClientController.getInstance().init();

	}

}
