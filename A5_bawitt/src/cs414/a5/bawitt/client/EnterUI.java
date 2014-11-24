package cs414.a5.bawitt.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs414.a5.bawitt.common.Controller;

public class EnterUI {
	private MainUI mainUI;
	private Controller controller;
		
	JLabel parkingRateLbl = new JLabel();
	public JPanel mainContentPnl = new JPanel(new GridLayout(3,1));

	public EnterUI(MainUI mainUI, Controller c ) {
		this.mainUI = mainUI;
		this.controller = c;
	}

	public void paintUI() {
		try{
			
		parkingRateLbl = new JLabel("Hourly Rate: $" + controller.getGarage().getGarageRate().getStandardRate());
		JButton getTicketBtn = new JButton("Get Ticket");
		JButton mainMenuBtn = new JButton("Main Menu");
		
		mainContentPnl.add(parkingRateLbl);
		mainContentPnl.add(getTicketBtn);
		mainContentPnl.add(mainMenuBtn);
		getTicketBtn.addActionListener(new EnterParkingUIListener());
		mainMenuBtn.addActionListener(new EnterParkingUIListener());

		mainContentPnl.setVisible(false);
		mainUI.mainPnl.add(mainContentPnl);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		}

	private class EnterParkingUIListener implements ActionListener {
		private EnterParkingUIListener() {
		}
		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Get Ticket")) {
				int t = controller.getGarage().issueTicket();
				JOptionPane.showMessageDialog(mainUI, "Ticket Number: " + t + "\nTime: " + controller.getTicketEnterDate(t) + "\nEntry Gate Open.",
							"Entry Gate Open.", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(mainUI, "Entry Gate Closed.", "Entry Gate Closed.", JOptionPane.INFORMATION_MESSAGE);
				mainUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
				mainUI.updateStatus();
			}
			if (e.getActionCommand().equals("Main Menu")) {
				mainUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
			}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}

