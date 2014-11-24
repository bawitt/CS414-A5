package cs414.a5.bawitt.client;

import java.awt.GridBagLayout;
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
import cs414.a5.bawitt.server.*;

public class ExitUI {
	private MainUI mainUI;
	private Controller controller;
	JPanel mainContentPnl = new JPanel(new GridBagLayout());
	private JTextField ticketTxt = new JTextField(10);
	private JPanel exitPnl = new JPanel(new GridLayout(5,1));
	private JPanel paymentChoicePnl = new JPanel(new GridLayout(4,1));
	private JPanel cashPnl = new JPanel(new GridLayout(5,1));
	private JPanel cardPnl = new JPanel(new GridLayout(7,1));
	private JLabel totalPaymentLbl = new JLabel("");
	private JLabel cashDueLbl = new JLabel("");
	private JLabel cardDueLbl = new JLabel("");
	private JTextField cashTxt = new JTextField(4);
	private JTextField ccNumTxt = new JTextField(10);
	private JTextField expDateTxt = new JTextField(10);
	private double totalCharge;
	private double amountDue;
	private int ticketNumber;
	private JButton homeBtn = new JButton("Main Menu");
	private JButton backBtn = new JButton("Back");
	private JButton backBtn1 = new JButton("Back");
	
	private DecimalFormat df = new DecimalFormat("0.00");

	public ExitUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		JLabel enterTicketLbl = new JLabel("Enter Ticket Number: ");
		JButton submitTicket = new JButton("Submit");
		JButton lostTicket = new JButton("Lost");
		exitPnl.add(enterTicketLbl);
		exitPnl.add(ticketTxt);
		exitPnl.add(submitTicket);
		exitPnl.add(lostTicket);
		exitPnl.add(homeBtn);
		submitTicket.addActionListener(new ExitUIListener());
		lostTicket.addActionListener(new ExitUIListener());
		homeBtn.addActionListener(new ExitUIListener());

		JLabel paymentChoiceLbl = new JLabel("Select Payment Type:");
		JButton cpBtn = new JButton("Cash");
		JButton epBtn = new JButton("Electronic");
		paymentChoicePnl.add(totalPaymentLbl);
		paymentChoicePnl.add(paymentChoiceLbl);
		paymentChoicePnl.add(cpBtn);
		paymentChoicePnl.add(epBtn);
		cpBtn.addActionListener(new ExitUIListener());
		epBtn.addActionListener(new ExitUIListener());
		paymentChoicePnl.setVisible(false);
		
		JButton submitCashBtn = new JButton("Submit Cash");
		cashPnl.add(cashDueLbl);
		cashPnl.add(new JLabel("Enter cash amount: "));
		cashPnl.add(cashTxt);
		cashPnl.add(submitCashBtn);
		cashPnl.add(backBtn);
		submitCashBtn.addActionListener(new ExitUIListener());
		cashPnl.setVisible(false);
		backBtn.addActionListener(new ExitUIListener());
		backBtn1.addActionListener(new ExitUIListener());
		
		JButton submitCreditBtn = new JButton("Submit ePay");
		cardPnl.add(cardDueLbl);
		cardPnl.add(new JLabel("Credit Card # (16 digits no dashes): "));
		cardPnl.add(ccNumTxt);
		cardPnl.add(new JLabel("Exp Date (MM/yyy): "));
		cardPnl.add(expDateTxt);
		cardPnl.add(submitCreditBtn);
		cardPnl.add(backBtn1);
		cardPnl.setVisible(false);
		submitCreditBtn.addActionListener(new ExitUIListener());

		mainContentPnl.add(exitPnl);
		mainContentPnl.add(paymentChoicePnl);
		mainContentPnl.add(cashPnl);
		mainContentPnl.add(cardPnl);

		mainContentPnl.setVisible(false);
		
		mainUI.mainPnl.add(mainContentPnl);
	}

	private void repaintUI() {
		ticketTxt.setText("");
		cashTxt.setText("");
		ccNumTxt.setText("");
		expDateTxt.setText("");
		exitPnl.setVisible(true);
		cashPnl.setVisible(false);
		cardPnl.setVisible(false);
		paymentChoicePnl.setVisible(false);
		homeBtn.setVisible(true);
	}

	private class ExitUIListener implements ActionListener {
		private ExitUIListener() {
		}
		public void actionPerformed(ActionEvent e) {
			try{
				
			String ticket = ticketTxt.getText();
			if(ticket.equals("")) ticketNumber = 0;
			else ticketNumber = Integer.parseInt(ticket);
			
			if(e.getActionCommand().equals("Cash")){
				paymentChoicePnl.setVisible(false);
				cashPnl.setVisible(true);
			}
			
			if(e.getActionCommand().equals("Electronic")){
				paymentChoicePnl.setVisible(false);
				cardPnl.setVisible(true);
			}
			
			if (e.getActionCommand().equals("Submit")) {
					if (controller.isTicketValid(ticketNumber)) { 
						totalCharge = controller.getAmountDueByID(ticketNumber);
						totalPaymentLbl.setText("Total: $" + df.format(totalCharge));
						amountDue = totalCharge;
						cashDueLbl.setText("Amount Due: " + amountDue);
						cardDueLbl.setText("Amount Due: " + amountDue);

						exitPnl.setVisible(false);
						paymentChoicePnl.setVisible(true);
						homeBtn.setVisible(false);
						backBtn.setVisible(true);					
					} else {
						JOptionPane.showMessageDialog(mainUI, "Invalid ticket number.", "Error", JOptionPane.ERROR_MESSAGE);
					}				
			}
			if (e.getActionCommand().equals("Lost")) {
				totalCharge = controller.getGarage().getGarageFlatRate();
				totalPaymentLbl.setText("Total: $" + df.format(totalCharge));
				amountDue = totalCharge;
				cashDueLbl.setText("Amount Due: $" + df.format(amountDue));
				cardDueLbl.setText("Amount Due: $" + df.format(amountDue));
				ticketNumber = 0; 

				exitPnl.setVisible(false);
				paymentChoicePnl.setVisible(true);
				homeBtn.setVisible(false);
				backBtn.setVisible(true);
			}
			if (e.getActionCommand().equals("Submit Cash")) {
				String cash = cashTxt.getText();
				if (cash.matches("[1-9]{1}[0-9]{0,3}")) {
					int cashAmount = Integer.parseInt(cash);
					if (cashAmount > amountDue) {
						JOptionPane.showMessageDialog(mainUI, "Change: $" + df.format((cashAmount - amountDue)), "Payment : Change", JOptionPane.INFORMATION_MESSAGE);
						amountDue = 0;
					}
					if (cashAmount == amountDue) {
						JOptionPane.showMessageDialog(mainUI, "Payment complete. Thank you.", "Payment : Complete", JOptionPane.INFORMATION_MESSAGE);
						amountDue = 0;
					}
					if (cashAmount < amountDue) {
						amountDue = amountDue - cashAmount;
					}
					cashDueLbl.setText("Amount Due: $" + df.format(amountDue));
					if (amountDue == 0) {
						controller.getGarage().exitGarage(ticketNumber);
						String receipt= controller.makeCashPayment(ticketNumber, totalCharge);
						JOptionPane.showMessageDialog(mainUI, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(mainUI, "Thank you. Exit Gate Open.", "Exit Gate Open.", JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(mainUI, "Exit Gate Closed.", "Exit Gate Closed.", JOptionPane.INFORMATION_MESSAGE);
						repaintUI();
						mainUI.mainContentPnl.setVisible(true);
						mainContentPnl.setVisible(false);
						mainUI.updateStatus();
					}
					cashTxt.setText("");
				} else {
					JOptionPane.showMessageDialog(mainUI, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (e.getActionCommand().equals("Submit ePay")) {
				String ccNum = ccNumTxt.getText();
				String expDate = expDateTxt.getText();
				ElectronicPaymentImpl ep = new ElectronicPaymentImpl(amountDue, ccNum, expDate, ticketNumber);
				if(ep.isAccountValid())
				{
					controller.getGarage().exitGarage(ticketNumber);
					String receipt= controller.makeElectronicPayment(amountDue, ccNum, expDate, ticketNumber);
					JOptionPane.showMessageDialog(mainUI, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(mainUI, "Thank you. Exit Gate Open.", "Exit Gate Open.", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(mainUI, "Exit Gate Closed.", "Exit Gate Closed.", JOptionPane.INFORMATION_MESSAGE);
					repaintUI();
					mainUI.mainContentPnl.setVisible(true);
					mainContentPnl.setVisible(false);
					mainUI.updateStatus();
				} 
				else JOptionPane.showMessageDialog(mainUI, "Payment invalid or unauthorized. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
				} 
			if (e.getActionCommand().equals("Main Menu")) {
				mainUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
			}
			if (e.getActionCommand().equals("Back")) {
				repaintUI();
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		}		
	}
}
