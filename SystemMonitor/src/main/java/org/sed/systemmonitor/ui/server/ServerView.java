package org.sed.systemmonitor.ui.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Dimension;

public class ServerView extends JFrame {

	private JPanel contentPane;
	private JTextField upTimeField;
	private JTextField packetsField;
	private JTextField lastUploadField;
	private int packageCount;
	private Date lastUpload;
	private ServerController serverController;
	private final Timer timer;

	public ServerView(ServerController serverController) {
		this.serverController = serverController;
		this.timer = new Timer();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 8.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setName("");
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Uptime");
		lblNewLabel.setPreferredSize(new Dimension(30, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		upTimeField = new JTextField();
		upTimeField.setEnabled(false);
		upTimeField.setEditable(false);
		GridBagConstraints gbc_upTimeField = new GridBagConstraints();
		gbc_upTimeField.fill = GridBagConstraints.BOTH;
		gbc_upTimeField.gridx = 1;
		gbc_upTimeField.gridy = 0;
		panel_1.add(upTimeField, gbc_upTimeField);
		upTimeField.setColumns(10);
		
		JPanel panel_1_1 = new JPanel();
		GridBagConstraints gbc_panel_1_1 = new GridBagConstraints();
		gbc_panel_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1_1.gridx = 0;
		gbc_panel_1_1.gridy = 1;
		panel.add(panel_1_1, gbc_panel_1_1);
		GridBagLayout gbl_panel_1_1 = new GridBagLayout();
		gbl_panel_1_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1_1.rowHeights = new int[]{0, 0};
		gbl_panel_1_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1_1.setLayout(gbl_panel_1_1);
		
		JLabel lblReceivedPackets = new JLabel("Received packets");
		lblReceivedPackets.setPreferredSize(new Dimension(30, 14));
		lblReceivedPackets.setMinimumSize(new Dimension(50, 50));
		lblReceivedPackets.setMaximumSize(new Dimension(50, 50));
		lblReceivedPackets.setHorizontalTextPosition(SwingConstants.LEFT);
		lblReceivedPackets.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblReceivedPackets = new GridBagConstraints();
		gbc_lblReceivedPackets.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblReceivedPackets.insets = new Insets(0, 0, 0, 5);
		gbc_lblReceivedPackets.gridx = 0;
		gbc_lblReceivedPackets.gridy = 0;
		panel_1_1.add(lblReceivedPackets, gbc_lblReceivedPackets);
		
		packetsField = new JTextField();
		packetsField.setEnabled(false);
		packetsField.setEditable(false);
		packetsField.setColumns(10);
		GridBagConstraints gbc_packetsField = new GridBagConstraints();
		gbc_packetsField.fill = GridBagConstraints.BOTH;
		gbc_packetsField.gridx = 1;
		gbc_packetsField.gridy = 0;
		panel_1_1.add(packetsField, gbc_packetsField);
		
		JPanel panel_1_2 = new JPanel();
		GridBagConstraints gbc_panel_1_2 = new GridBagConstraints();
		gbc_panel_1_2.fill = GridBagConstraints.BOTH;
		gbc_panel_1_2.gridx = 0;
		gbc_panel_1_2.gridy = 2;
		panel.add(panel_1_2, gbc_panel_1_2);
		GridBagLayout gbl_panel_1_2 = new GridBagLayout();
		gbl_panel_1_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1_2.rowHeights = new int[]{0, 0};
		gbl_panel_1_2.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1_2.setLayout(gbl_panel_1_2);
		
		JLabel lblLastUpload = new JLabel("Last upload");
		lblLastUpload.setPreferredSize(new Dimension(30, 14));
		GridBagConstraints gbc_lblLastUpload = new GridBagConstraints();
		gbc_lblLastUpload.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLastUpload.insets = new Insets(0, 0, 0, 5);
		gbc_lblLastUpload.gridx = 0;
		gbc_lblLastUpload.gridy = 0;
		panel_1_2.add(lblLastUpload, gbc_lblLastUpload);
		
		lastUploadField = new JTextField();
		lastUploadField.setEnabled(false);
		lastUploadField.setEditable(false);
		lastUploadField.setColumns(10);
		GridBagConstraints gbc_lastUploadField = new GridBagConstraints();
		gbc_lastUploadField.fill = GridBagConstraints.BOTH;
		gbc_lastUploadField.gridx = 1;
		gbc_lastUploadField.gridy = 0;
		panel_1_2.add(lastUploadField, gbc_lastUploadField);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton stopServerButton = new JButton("Stop Server");
		stopServerButton.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_stopServerButton = new GridBagConstraints();
		gbc_stopServerButton.fill = GridBagConstraints.BOTH;
		gbc_stopServerButton.gridx = 0;
		gbc_stopServerButton.gridy = 2;
		contentPane.add(stopServerButton, gbc_stopServerButton);
		uptimeTimer();
	}
	
	public void uploadHappened() {
		packetsField.setText(serverController.packets+"");
		lastUploadField.setText(new Date().toString());
		
	}
	
	public void uptimeTimer() {
		
		TimerTask taskGotoGame = new TimerTask() {
            public void run() {
            	RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        		long uptime = rb.getUptime();
        		int seconds = (int) (uptime / 1000) % 60 ;
        		int minutes = (int) ((uptime / (1000*60)) % 60);
        		int hours   = (int) ((uptime / (1000*60*60)) % 24);
        		
        		upTimeField.setText("Hours: "+hours + " Minutes: "+minutes + " Seconds: "+ seconds);
            }
        };
        timer.schedule(taskGotoGame, 0, 1000);
		
	}

}
