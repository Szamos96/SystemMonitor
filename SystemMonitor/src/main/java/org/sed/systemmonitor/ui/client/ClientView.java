package org.sed.systemmonitor.ui.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTree;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.sed.systemmonitor.model.SystemInformation;
import org.sed.systemmonitor.util.JTreeMapper;
import org.sed.systemmonitor.util.SystemInformationFactory;
import org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver;

import oshi.SystemInfo;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ClientView extends JFrame {

	private JPanel contentPane;
	private JTextField txtHttplocalhost;
	private JTextField txtPort;
	public JTextField remainField;
	private JDateChooser endDate;
	private JDateChooser startDate;
	private JTree systemInfoTree;

	public ClientView(ClientController clientController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Hardware information", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(JTreeMapper
				.createFromHardware(SystemInformationFactory.getInstance().createSystemInformation().getHardware())));
		scrollPane.setViewportView(tree);

		JPanel panel_1 = new ProcessorPanel(new SystemInfo());
		tabbedPane.addTab("CPU", null, panel_1, null);

		JPanel panel_2 = new MemoryPanel(new SystemInfo());
		tabbedPane.addTab("Memory", null, panel_2, null);
		
		JPanel panel_3_1 = new JPanel();
		tabbedPane.addTab("Settings", null, panel_3_1, null);
		GridBagLayout gbl_panel_3_1 = new GridBagLayout();
		gbl_panel_3_1.columnWidths = new int[]{0, 0};
		gbl_panel_3_1.rowHeights = new int[]{0, 0};
		gbl_panel_3_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3_1.setLayout(gbl_panel_3_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setToolTipText("");
		panel_4.setName("");
		panel_4.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel_3_1.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JPanel panel_1_1 = new JPanel();
		GridBagConstraints gbc_panel_1_1 = new GridBagConstraints();
		gbc_panel_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1_1.gridx = 0;
		gbc_panel_1_1.gridy = 0;
		panel_4.add(panel_1_1, gbc_panel_1_1);
		GridBagLayout gbl_panel_1_1 = new GridBagLayout();
		gbl_panel_1_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1_1.rowHeights = new int[]{0, 0};
		gbl_panel_1_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1_1.setLayout(gbl_panel_1_1);
		
		JLabel lblIp = new JLabel("Ip");
		lblIp.setPreferredSize(new Dimension(30, 14));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIp.insets = new Insets(0, 0, 0, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		panel_1_1.add(lblIp, gbc_lblIp);
		
		txtHttplocalhost = new JTextField();
		txtHttplocalhost.setText("http://localhost");
		txtHttplocalhost.setColumns(10);
		GridBagConstraints gbc_txtHttplocalhost = new GridBagConstraints();
		gbc_txtHttplocalhost.fill = GridBagConstraints.BOTH;
		gbc_txtHttplocalhost.gridx = 1;
		gbc_txtHttplocalhost.gridy = 0;
		panel_1_1.add(txtHttplocalhost, gbc_txtHttplocalhost);
		
		JPanel panel_1_1_1 = new JPanel();
		GridBagConstraints gbc_panel_1_1_1 = new GridBagConstraints();
		gbc_panel_1_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1_1_1.gridx = 0;
		gbc_panel_1_1_1.gridy = 1;
		panel_4.add(panel_1_1_1, gbc_panel_1_1_1);
		GridBagLayout gbl_panel_1_1_1 = new GridBagLayout();
		gbl_panel_1_1_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1_1_1.rowHeights = new int[]{0, 0};
		gbl_panel_1_1_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1_1_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1_1_1.setLayout(gbl_panel_1_1_1);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setPreferredSize(new Dimension(30, 14));
		lblPort.setMinimumSize(new Dimension(50, 50));
		lblPort.setMaximumSize(new Dimension(50, 50));
		lblPort.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPort.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPort.insets = new Insets(0, 0, 0, 5);
		gbc_lblPort.gridx = 0;
		gbc_lblPort.gridy = 0;
		panel_1_1_1.add(lblPort, gbc_lblPort);
		
		txtPort = new JTextField();
		txtPort.setText("8080");
		txtPort.setColumns(10);
		GridBagConstraints gbc_txtPort = new GridBagConstraints();
		gbc_txtPort.fill = GridBagConstraints.BOTH;
		gbc_txtPort.gridx = 1;
		gbc_txtPort.gridy = 0;
		panel_1_1_1.add(txtPort, gbc_txtPort);
		
		JPanel panel_1_2 = new JPanel();
		GridBagConstraints gbc_panel_1_2 = new GridBagConstraints();
		gbc_panel_1_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1_2.fill = GridBagConstraints.BOTH;
		gbc_panel_1_2.gridx = 0;
		gbc_panel_1_2.gridy = 2;
		panel_4.add(panel_1_2, gbc_panel_1_2);
		GridBagLayout gbl_panel_1_2 = new GridBagLayout();
		gbl_panel_1_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1_2.rowHeights = new int[]{0, 0};
		gbl_panel_1_2.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1_2.setLayout(gbl_panel_1_2);
		
		JLabel lblUploadFrequency = new JLabel("Upload frequency in min");
		lblUploadFrequency.setMinimumSize(new Dimension(50, 14));
		lblUploadFrequency.setMaximumSize(new Dimension(50, 14));
		lblUploadFrequency.setPreferredSize(new Dimension(30, 14));
		GridBagConstraints gbc_lblUploadFrequency = new GridBagConstraints();
		gbc_lblUploadFrequency.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUploadFrequency.insets = new Insets(0, 0, 0, 5);
		gbc_lblUploadFrequency.gridx = 0;
		gbc_lblUploadFrequency.gridy = 0;
		panel_1_2.add(lblUploadFrequency, gbc_lblUploadFrequency);
		
		JSpinner txtFreq = new JSpinner();
		txtFreq.setModel(new SpinnerNumberModel(new Integer(60), null, null, new Integer(1)));
		txtFreq.setPreferredSize(new Dimension(85, 20));
		GridBagConstraints gbc_txtFreq = new GridBagConstraints();
		gbc_txtFreq.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFreq.gridx = 1;
		gbc_txtFreq.gridy = 0;
		panel_1_2.add(txtFreq, gbc_txtFreq);
		
		JButton btnNewButton = new JButton("Test");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(clientController.test()) {
						JOptionPane.showMessageDialog(ClientView.this,"Server is available");
					}else {
						JOptionPane.showMessageDialog(ClientView.this,"Database is unavailable");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(ClientView.this,"Server is unavailable");
				}
			}
		});
		
		JPanel panel_1_1_1_1 = new JPanel();
		GridBagConstraints gbc_panel_1_1_1_1 = new GridBagConstraints();
		gbc_panel_1_1_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1_1_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1_1_1_1.gridx = 0;
		gbc_panel_1_1_1_1.gridy = 3;
		panel_4.add(panel_1_1_1_1, gbc_panel_1_1_1_1);
		GridBagLayout gbl_panel_1_1_1_1 = new GridBagLayout();
		gbl_panel_1_1_1_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1_1_1_1.rowHeights = new int[]{0, 0};
		gbl_panel_1_1_1_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1_1_1_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1_1_1_1.setLayout(gbl_panel_1_1_1_1);
		
		JLabel lblTimeRemainsIn = new JLabel("Time remains in min");
		lblTimeRemainsIn.setPreferredSize(new Dimension(30, 14));
		lblTimeRemainsIn.setMinimumSize(new Dimension(50, 50));
		lblTimeRemainsIn.setMaximumSize(new Dimension(50, 50));
		lblTimeRemainsIn.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTimeRemainsIn.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblTimeRemainsIn = new GridBagConstraints();
		gbc_lblTimeRemainsIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTimeRemainsIn.insets = new Insets(0, 0, 0, 5);
		gbc_lblTimeRemainsIn.gridx = 0;
		gbc_lblTimeRemainsIn.gridy = 0;
		panel_1_1_1_1.add(lblTimeRemainsIn, gbc_lblTimeRemainsIn);
		
		remainField = new JTextField();
		remainField.setHorizontalAlignment(SwingConstants.RIGHT);
		remainField.setEnabled(false);
		remainField.setEditable(false);
		remainField.setText("0");
		remainField.setColumns(10);
		GridBagConstraints gbc_remainField = new GridBagConstraints();
		gbc_remainField.fill = GridBagConstraints.BOTH;
		gbc_remainField.gridx = 1;
		gbc_remainField.gridy = 0;
		panel_1_1_1_1.add(remainField, gbc_remainField);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		panel_4.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clientController.freq = (int) txtFreq.getModel().getValue();
				clientController.ip = txtHttplocalhost.getText();
				clientController.port = Integer.parseInt(txtPort.getText());
				
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 5;
		panel_4.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Start");
		btnNewButton_1_1.setForeground(Color.BLACK);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(clientController.start()) {
					btnNewButton_1_1.setText("STOP");
					btnNewButton_1_1.setForeground(Color.RED);
				}else {
					btnNewButton_1_1.setText("Start");
					btnNewButton_1_1.setForeground(Color.BLACK);
					remainField.setText("0");
				}
				
				
			}
		});
		GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
		gbc_btnNewButton_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1_1.gridx = 0;
		gbc_btnNewButton_1_1.gridy = 6;
		panel_4.add(btnNewButton_1_1, gbc_btnNewButton_1_1);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Query data", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		startDate = new JDateChooser();
		startDate.setDate(new Date());
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 0;
		gbc_dateChooser.gridy = 0;
		panel_3.add(startDate, gbc_dateChooser);
		
		
		endDate = new JDateChooser();
		endDate.setDate(new Date());
		GridBagConstraints gbc_dateChooser_1 = new GridBagConstraints();
		gbc_dateChooser_1.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser_1.fill = GridBagConstraints.BOTH;
		gbc_dateChooser_1.gridx = 1;
		gbc_dateChooser_1.gridy = 0;
		panel_3.add(endDate, gbc_dateChooser_1);
		
		JButton btnNewButton_4 = new JButton("Get");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				System.out.println(startDate.getDate());
				System.out.println(endDate.getDate());
				
				try {
					List<SystemInformation> betweenDates = clientController.getBetweenDates(endDate.getDate(), startDate.getDate());
					
					systemInfoTree.setModel(new DefaultTreeModel(JTreeMapper.createFromSystemInformationList(betweenDates)));
					
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.gridwidth = 2;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 1;
		panel_3.add(btnNewButton_4, gbc_btnNewButton_4);
		
		systemInfoTree = new JTree();
		systemInfoTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("SystemInformations") {
				{
				}
			}
		));
		GridBagConstraints gbc_systemInfoTree = new GridBagConstraints();
		gbc_systemInfoTree.gridwidth = 2;
		gbc_systemInfoTree.fill = GridBagConstraints.BOTH;
		gbc_systemInfoTree.gridx = 0;
		gbc_systemInfoTree.gridy = 2;
		panel_3.add(systemInfoTree, gbc_systemInfoTree);
	}

}
