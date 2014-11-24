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

public class LoginUI {
	private MainUI mainUI;
	private Controller controller;
	JPanel mainContentPnl = new JPanel(new GridLayout(6,1));
	private JTextField usernameTxt = new JTextField(10);
	private JTextField pwdTxt = new JTextField(10);
	private JButton mainMenuBtn = new JButton("Main Menu");
	private String username;

	public LoginUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		JButton loginBtn = new JButton("Login");
		mainContentPnl.add(new JLabel("Username: "));
		mainContentPnl.add(usernameTxt);
		mainContentPnl.add(new JLabel("Password: "));
		mainContentPnl.add(pwdTxt);
		mainContentPnl.add(loginBtn);
		mainContentPnl.add(mainMenuBtn);
		loginBtn.addActionListener(new LoginUIListener());
		mainMenuBtn.addActionListener(new LoginUIListener());

		mainContentPnl.setVisible(false);
		mainUI.mainPnl.add(mainContentPnl);
	}

	private void repaintUI() {
		mainContentPnl.setVisible(true);
		mainMenuBtn.setVisible(true);
		usernameTxt.setText("");
		pwdTxt.setText("");
		username = "";
	}

	private class LoginUIListener implements ActionListener {
		private LoginUIListener() {
		}
		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Login")) {
				username = usernameTxt.getText();
				String password = pwdTxt.getText();
					if (controller.getGarage().authorizeUser(username, password)) {
						repaintUI();
						mainUI.adminUI.mainContentPnl.setVisible(true);
						mainContentPnl.setVisible(false);
						
					} else {
						JOptionPane.showMessageDialog(mainUI, "Incorrect username/password.", "Error", JOptionPane.ERROR_MESSAGE);
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
