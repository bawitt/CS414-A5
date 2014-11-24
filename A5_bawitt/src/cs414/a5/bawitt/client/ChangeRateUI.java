package cs414.a5.bawitt.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import javax.swing.JTextField;

import cs414.a5.bawitt.common.Controller;

public class ChangeRateUI {
	private MainUI mainUI;
	private Controller controller;
	JPanel mainContentPnl = new JPanel(new GridLayout(8,1));
	private JTextField hourlyTxt = new JTextField(10);
	private JTextField flatTxt = new JTextField(10);
	private JButton updateBtn = new JButton("Update");
	private JButton backBtn = new JButton("Back");
	JLabel parkingRateHourlyLbl;
	JLabel parkingRateFlatLbl;
	private DecimalFormat df = new DecimalFormat("0.00");

	public ChangeRateUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		try{
		parkingRateHourlyLbl = new JLabel("Hourly Rate: $" + df.format(controller.getGarage().getGarageRate().getStandardRate()));
		parkingRateFlatLbl = new JLabel("Lost Ticket Rate: $" + df.format(controller.getGarage().getGarageRate().getFlatRate()));
		
		mainContentPnl.add(parkingRateHourlyLbl);
		mainContentPnl.add(parkingRateFlatLbl);
		mainContentPnl.add(new JLabel("New Hourly Rate: "));
		mainContentPnl.add(hourlyTxt);
		mainContentPnl.add(new JLabel("New Flat Rate: "));
		mainContentPnl.add(flatTxt);
		mainContentPnl.add(updateBtn);
		mainContentPnl.add(backBtn);
		updateBtn.addActionListener(new ChangeRateUIListener());
		backBtn.addActionListener(new ChangeRateUIListener());

		mainContentPnl.setVisible(false);
		mainUI.mainPnl.add(mainContentPnl);
		
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	private void repaintUI() {
		try{
		mainContentPnl.setVisible(true);
		backBtn.setVisible(true);
		hourlyTxt.setText("");
		flatTxt.setText("");
		parkingRateHourlyLbl.setText("Hourly Rate: $" + df.format(controller.getGarage().getGarageRate().getStandardRate()));
		parkingRateFlatLbl.setText("Lost Ticket Rate: $" + df.format(controller.getGarage().getGarageRate().getFlatRate()));
		} catch (RemoteException e1) {
			e1.printStackTrace();
	}
	}

	private class ChangeRateUIListener implements ActionListener {
		ChangeRateUIListener() {
		}

		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Update")) {
				double hourlyRate, flatRate;
				if(hourlyTxt.getText().equals("")) hourlyRate=controller.getGarage().getGarageRate().getStandardRate();
				else hourlyRate = Double.parseDouble(hourlyTxt.getText());
				if(flatTxt.getText().equals("")) flatRate=controller.getGarage().getGarageRate().getFlatRate();
				else flatRate = Double.parseDouble(flatTxt.getText());
				controller.getGarage().updateGarageStandardRate(hourlyRate);
				controller.getGarage().updateGarageFlatRate(flatRate);
				JOptionPane.showMessageDialog(mainUI, "Rate(s) updated.", "Message", JOptionPane.INFORMATION_MESSAGE);
				repaintUI();
			}
			if (e.getActionCommand().equals("Back")) {
				mainUI.adminUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);				
			}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}
