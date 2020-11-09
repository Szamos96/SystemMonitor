package org.sed.systemmonitor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SystemMonitorApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SystemMonitorApplication window = new SystemMonitorApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SystemMonitorApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JButton startServerButton = new JButton("Start Server");
		startServerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new SystemMonitorServer().startServer();
				
			}
		});
		startServerButton.setFont(new Font("Tahoma", Font.PLAIN, 50));
		GridBagConstraints gbc_startServerButton = new GridBagConstraints();
		gbc_startServerButton.fill = GridBagConstraints.BOTH;
		gbc_startServerButton.insets = new Insets(0, 0, 5, 0);
		gbc_startServerButton.gridx = 0;
		gbc_startServerButton.gridy = 0;
		frame.getContentPane().add(startServerButton, gbc_startServerButton);

		JButton startClientButton = new JButton("Start Client");
		startClientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new SystemMonitorClient().startClient();
				
			}
		});
		startClientButton.setFont(new Font("Tahoma", Font.PLAIN, 50));
		GridBagConstraints gbc_startClientButton = new GridBagConstraints();
		gbc_startClientButton.fill = GridBagConstraints.BOTH;
		gbc_startClientButton.gridx = 0;
		gbc_startClientButton.gridy = 1;
		frame.getContentPane().add(startClientButton, gbc_startClientButton);
	}

}
