package cs414.a5.bawitt.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.*;

import cs414.a5.bawitt.common.Controller;
import cs414.a5.bawitt.server.*;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private EnterUI enterUI;
	private ExitUI exitUI;
	private LostTicketUI lostTicketUI;
	private SpaceStatusUI spaceStatusUI;
	private LoginUI loginUI;
	public AdminUI adminUI;
	public ReportsUI reportsUI;
	public ChangeRateUI changeRateUI;
	public ChangeSpaceUI changeSpaceUI;
	public JPanel mainPnl = new JPanel(new GridBagLayout());
	public JPanel mainContentPnl = new JPanel(new GridLayout(6,1));
	private JLabel statusLbl = new JLabel("");
	private DecimalFormat df = new DecimalFormat("0.00");

	private static MainUI instance = null;

	public MainUI(Controller controller) {
		this.controller = controller;
		paintUIs();
	}

	public static MainUI getInstance(Controller controller) {
		if (instance == null) {
			instance = new MainUI(controller);
		}
		return instance;
	}

	private void paintUIs() {
		enterUI = new EnterUI(this, controller);
		exitUI = new ExitUI(this, controller);
		lostTicketUI = new LostTicketUI(this, controller);
		spaceStatusUI = new SpaceStatusUI(this, controller);
		loginUI = new LoginUI(this, controller);
		adminUI = new AdminUI(this, controller);
		changeRateUI = new ChangeRateUI(this, controller);
		changeSpaceUI = new ChangeSpaceUI(this, controller);
		reportsUI = new ReportsUI (this, controller);		
	}

	public void paintUI() {
		paintMainUI();
		enterUI.paintUI();
		exitUI.paintUI();
		lostTicketUI.paintUI();
		spaceStatusUI.paintUI();
		loginUI.paintUI();
		adminUI.paintUI();
		changeRateUI.paintUI();
		changeSpaceUI.paintUI();
		reportsUI.paintUI();
	}

	private void paintMainUI() {
		setSize(300, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPnl);
		setVisible(true);
		
		updateStatus();

		JButton enterParkingBtn = new JButton("Enter Garage");
		JButton exitParkingBtn = new JButton("Exit Garage");
		JButton spaceStatusBtn = new JButton("Space Status");
		JButton cantPayBtn = new JButton("Can't Pay");
		JButton loginBtn = new JButton("Login");
		mainContentPnl.add(enterParkingBtn);
		mainContentPnl.add(exitParkingBtn);
		mainContentPnl.add(spaceStatusBtn);
		mainContentPnl.add(cantPayBtn);
		mainContentPnl.add(loginBtn);
		mainContentPnl.add(statusLbl);

		enterParkingBtn.addActionListener(new MainUIListener());
		exitParkingBtn.addActionListener(new MainUIListener());
		spaceStatusBtn.addActionListener(new MainUIListener());
		cantPayBtn.addActionListener(new MainUIListener());
		loginBtn.addActionListener(new MainUIListener());

		mainPnl.add(mainContentPnl);
	}

	public void updateStatus() {
		try{
		statusLbl.setText("Garage Status: " + controller.getGarage().getSignStatus());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	private class MainUIListener implements ActionListener {

		private MainUIListener() {
		}

		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Enter Garage")) {
				if (controller.getGarage().getSignStatus()!=SignStatusImpl.full) {
					enterUI.mainContentPnl.setVisible(true);
					mainContentPnl.setVisible(false);
					enterUI.parkingRateLbl.setText("Hourly Rate: $" + df.format(controller.getGarage().getGarageRate().getStandardRate()));
				} else {
					statusLbl.setText("Garage Status: " + controller.getGarage().getSignStatus());
					JOptionPane.showMessageDialog(getInstance(controller), "Garage is full.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (e.getActionCommand().equals("Exit Garage")) {
					exitUI.mainContentPnl.setVisible(true);
					mainContentPnl.setVisible(false);
				}

			if (e.getActionCommand().equals("Can't Pay")) {
				lostTicketUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
			}
			
			if (e.getActionCommand().equals("Space Status")) {
				spaceStatusUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
				spaceStatusUI.usedLbl.setText("Used Spaces: " + controller.getGarage().getUsedSpaces());
				spaceStatusUI.totalLbl.setText("Total Spaces: " + controller.getGarage().getTotalSpaces());
			}
			
			if (e.getActionCommand().equals("Login")) {
				loginUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
			}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}

