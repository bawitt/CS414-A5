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

public class AdminUI {
	private MainUI mainUI;
	private Controller controller;
	public JPanel mainContentPnl = new JPanel();
	private JPanel adminPnl = new JPanel(new GridLayout(6,1));
	private JPanel addAccountPnl = new JPanel(new GridLayout(6,1));
	private JPanel deactivateActPnl = new JPanel(new GridLayout(4,1));

	private JTextField addUsernameTxt = new JTextField(10);
	private JTextField passwordTxt = new JTextField(10);
	private JTextField dUsernameTxt = new JTextField(10);
	
	private JButton backBtn = new JButton("Back");
	
	private DecimalFormat df = new DecimalFormat("0.00");

	public AdminUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		mainContentPnl.setVisible(false);

		JButton configRatesBtn = new JButton("Update Rate");
		JButton configSizeBtn = new JButton("Update Spaces");
		JButton viewReportsBtn = new JButton("Reports");
		JButton addAccountBtn = new JButton("Add Account");
		JButton deactivateAccountBtn = new JButton("Disable Account");
		JButton mainBtn = new JButton("Main Menu");

		adminPnl.add(configRatesBtn);
		adminPnl.add(configSizeBtn);
		adminPnl.add(viewReportsBtn);
		adminPnl.add(addAccountBtn);
		adminPnl.add(deactivateAccountBtn);
		adminPnl.add(mainBtn);
		
		configRatesBtn.addActionListener(new AdminUIListener());
		configSizeBtn.addActionListener(new AdminUIListener());
		viewReportsBtn.addActionListener(new AdminUIListener());
		addAccountBtn.addActionListener(new AdminUIListener());
		deactivateAccountBtn.addActionListener(new AdminUIListener());
		mainBtn.addActionListener(new AdminUIListener());
		
		JButton createAccountBtn = new JButton("Create");
		addAccountPnl.add(new JLabel("New Username: "));
		addAccountPnl.add(addUsernameTxt);
		addAccountPnl.add(new JLabel("New Password: "));
		addAccountPnl.add(passwordTxt);
		addAccountPnl.add(createAccountBtn);
		addAccountPnl.add(backBtn);
		createAccountBtn.addActionListener(new AdminUIListener());
		backBtn.addActionListener(new AdminUIListener());

		JButton deactivateBtn = new JButton("Deactivate");
		JButton backBtn2 = new JButton("Back");
		deactivateActPnl.add(new JLabel("Username To Deactivate:"));
		deactivateActPnl.add(dUsernameTxt);
		deactivateActPnl.add(deactivateBtn);
		deactivateActPnl.add(backBtn2);
		deactivateBtn.addActionListener(new AdminUIListener());
		backBtn2.addActionListener(new AdminUIListener());

		mainContentPnl.add(adminPnl);
		mainContentPnl.add(addAccountPnl);
		mainContentPnl.add(deactivateActPnl);

		addAccountPnl.setVisible(false);
		deactivateActPnl.setVisible(false);
		
		mainUI.mainPnl.add(mainContentPnl);
	}

	public void repaintUI() {
		adminPnl.setVisible(true);
		addAccountPnl.setVisible(false);
		deactivateActPnl.setVisible(false);
	}

	private class AdminUIListener implements ActionListener {
		private AdminUIListener() {
		}

		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Update Rate")) {
				repaintUI();
				mainUI.changeRateUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
				mainUI.changeRateUI.parkingRateHourlyLbl.setText("Hourly Rate: $" + df.format(controller.getGarage().getGarageRate().getStandardRate()));
				mainUI.changeRateUI.parkingRateFlatLbl.setText("Lost Ticket Rate: $" + df.format(controller.getGarage().getGarageRate().getFlatRate()));
			}
			
			if (e.getActionCommand().equals("Update Spaces")) {
				repaintUI();
				mainUI.changeSpaceUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
				mainUI.changeSpaceUI.parkingSpacesLbl.setText("Total Spaces: " + controller.getGarage().getTotalSpaces());
			}			
			if (e.getActionCommand().equals("Reports")) {
				repaintUI();
				mainUI.reportsUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
			}			
			if (e.getActionCommand().equals("Add Account")) {
				adminPnl.setVisible(false);
				addAccountPnl.setVisible(true);
				backBtn.setVisible(true);
			}
			if (e.getActionCommand().equals("Create")) {
				String un = addUsernameTxt.getText();
				String pw = passwordTxt.getText();
				if(un.length()>0 && pw.length()>0)
				{
					if(controller.getGarage().createEmployee(un, pw))
					{
						JOptionPane.showMessageDialog(mainUI, "Account added.", "Message", JOptionPane.INFORMATION_MESSAGE);
						repaintUI();
					} 
					else JOptionPane.showMessageDialog(mainUI, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);	
				}
				else JOptionPane.showMessageDialog(mainUI, "Invalid username/password format.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (e.getActionCommand().equals("Disable Account")) {
				adminPnl.setVisible(false);
				deactivateActPnl.setVisible(true);
				backBtn.setVisible(true);
			}
			if (e.getActionCommand().equals("Deactivate")) 
			{
				String un = dUsernameTxt.getText();
				controller.getGarage().getEmployeeFromList(un).deactivateUser();
				JOptionPane.showMessageDialog(mainUI, "Account Disabled.", "Message", JOptionPane.ERROR_MESSAGE);
				repaintUI();
			}			
			if (e.getActionCommand().equals("Back")) {
				repaintUI();
			}
			if (e.getActionCommand().equals("Main Menu")) {
				mainUI.updateStatus();
				mainUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
			}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}