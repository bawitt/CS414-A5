package cs414.a5.bawitt.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs414.a5.bawitt.common.Controller;

public class ReportsUI {
	private MainUI mainUI;
	private Controller controller;
	public JPanel mainContentPnl = new JPanel();
	private JPanel reportsPnl = new JPanel(new GridLayout(4,1));
	private JButton backBtn = new JButton("Back");
	private JButton mainBtn = new JButton("Main Menu");

	public ReportsUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		JButton unpaidTicketsBtn = new JButton("Unpaid Tickets");
		reportsPnl.add(unpaidTicketsBtn);
		reportsPnl.add(backBtn);
		reportsPnl.add(mainBtn);
		unpaidTicketsBtn.addActionListener(new ReportsUIListener());
		backBtn.addActionListener(new ReportsUIListener());
		mainBtn.addActionListener(new ReportsUIListener());

		mainContentPnl.add(reportsPnl);

		mainContentPnl.setVisible(false);
		mainUI.mainPnl.add(mainContentPnl);
	}

	public void repaintUI() {
		reportsPnl.setVisible(true);
	}

	private class ReportsUIListener implements ActionListener {
		private ReportsUIListener() {
		}

		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Unpaid Tickets")) {
				repaintUI();
				String utString = controller.getGarage().showUnpaidTickets();
				JOptionPane.showMessageDialog(mainUI, utString, "Unpaid Tickets", JOptionPane.INFORMATION_MESSAGE);
			}		
			if (e.getActionCommand().equals("Back")) {
				mainUI.adminUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
			}
			if (e.getActionCommand().equals("Main Menu")) {
				mainUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
				mainUI.updateStatus();
			}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}