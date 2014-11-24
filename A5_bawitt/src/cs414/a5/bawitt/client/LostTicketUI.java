package cs414.a5.bawitt.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs414.a5.bawitt.common.Controller;

public class LostTicketUI {
	private MainUI mainUI;
	private Controller controller;
	JPanel mainContentPnl = new JPanel(new GridLayout(10,1));
	private JTextField nameTxt = new JTextField(10);
	private JTextField addressTxt = new JTextField(10);
	private JTextField phoneTxt = new JTextField(10);
	private JTextField ticketIDTxt = new JTextField(10);
	private JButton mainMenuBtn = new JButton("Main Menu");
	private String name;
	private String address;
	private String phone;
	private int ticketID;

	public LostTicketUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		JButton submitBtn = new JButton("Submit");
		mainContentPnl.add(new JLabel("Name: "));
		mainContentPnl.add(nameTxt);
		mainContentPnl.add(new JLabel("Address: "));
		mainContentPnl.add(addressTxt);
		mainContentPnl.add(new JLabel("Phone: "));
		mainContentPnl.add(phoneTxt);
		mainContentPnl.add(new JLabel("Ticket ID (0 for lost ticket): "));
		mainContentPnl.add(ticketIDTxt);
		mainContentPnl.add(submitBtn);
		mainContentPnl.add(mainMenuBtn);
		submitBtn.addActionListener(new LoginUIListener());
		mainMenuBtn.addActionListener(new LoginUIListener());

		mainContentPnl.setVisible(false);
		mainUI.mainPnl.add(mainContentPnl);
	}

	private void repaintUI() {
		mainContentPnl.setVisible(true);
		mainMenuBtn.setVisible(true);
		nameTxt.setText("");
		addressTxt.setText("");
		phoneTxt.setText("");
		ticketIDTxt.setText("");		
	}

	private class LoginUIListener implements ActionListener {
		private LoginUIListener() {
		}

		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Submit")) {
				name = nameTxt.getText();
				address = addressTxt.getText();
				phone= phoneTxt.getText();
				try{
					ticketID = Integer.parseInt(ticketIDTxt.getText());
					if (controller.isTicketValid(ticketID) || ticketID==0) {
						controller.exitWithoutPayment(name, address, phone, ticketID);
						repaintUI();
						JOptionPane.showMessageDialog(mainUI, "Exit Gate Open.", "Exit Gate Open", JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(mainUI, "Exit Gate Closed.", "Exit Gate Closed", JOptionPane.INFORMATION_MESSAGE);
						mainUI.mainContentPnl.setVisible(true);
						mainContentPnl.setVisible(false);
						mainUI.updateStatus();
					} else {
						JOptionPane.showMessageDialog(mainUI, "Invalid ticket number.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(mainUI, "Invalid ticket number format.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} 
			if (e.getActionCommand().equals("Main Menu")) {
				repaintUI();
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
