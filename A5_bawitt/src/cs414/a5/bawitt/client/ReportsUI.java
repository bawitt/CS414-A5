package cs414.a5.bawitt.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs414.a5.bawitt.common.Controller;

public class ReportsUI {
	private MainUI mainUI;
	private Controller controller;
	public JPanel mainContentPnl = new JPanel();
	private JPanel reportsPnl = new JPanel(new GridLayout(6,1));
	private JButton backBtn = new JButton("Back");
	private JButton mainBtn = new JButton("Main Menu");
	private DecimalFormat df = new DecimalFormat("0.00");

	public ReportsUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		JButton unpaidTicketsBtn = new JButton("Unpaid Tickets");
		JButton amountOwedBtn = new JButton("Amount Owed To Garage");
		JButton avLengthStayBtn = new JButton("Average Length of Stay");
		JButton totalRevenueBtn = new JButton("Total Revenue");
		reportsPnl.add(unpaidTicketsBtn);
		reportsPnl.add(amountOwedBtn);
		reportsPnl.add(avLengthStayBtn);
		reportsPnl.add(totalRevenueBtn);
		reportsPnl.add(backBtn);
		reportsPnl.add(mainBtn);
		unpaidTicketsBtn.addActionListener(new ReportsUIListener());
		amountOwedBtn.addActionListener(new ReportsUIListener());
		avLengthStayBtn.addActionListener(new ReportsUIListener());
		totalRevenueBtn.addActionListener(new ReportsUIListener());
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
			if (e.getActionCommand().equals("Amount Owed To Garage")) {
				repaintUI();
				double owed= controller.getGarage().getAmountOwedToGarage();
				String owedString = "$" + df.format(owed);
				JOptionPane.showMessageDialog(mainUI, owedString, "Amount Owed To Garage", JOptionPane.INFORMATION_MESSAGE);
			}		
			if (e.getActionCommand().equals("Average Length of Stay")) {
				repaintUI();
				double stay= controller.getGarage().getAverageLengthOfStay();
				String stayString = df.format(stay) + " hours";
				JOptionPane.showMessageDialog(mainUI, stayString, "Average Length of Stay", JOptionPane.INFORMATION_MESSAGE);
			}
			if (e.getActionCommand().equals("Total Revenue")) {
				repaintUI();
				double rev= controller.getGarage().getTotalRevenue();
				String revString = "$" + df.format(rev);
				JOptionPane.showMessageDialog(mainUI, revString, "Total Revenue", JOptionPane.INFORMATION_MESSAGE);
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