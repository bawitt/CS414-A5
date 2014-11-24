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

public class ChangeSpaceUI {
	private MainUI mainUI;
	private Controller controller;
	JPanel mainContentPnl = new JPanel(new GridLayout(5,1));
	private JTextField spacesTxt = new JTextField(10);
	private JButton updateBtn = new JButton("Update");
	private JButton backBtn = new JButton("Back");
	JLabel parkingSpacesLbl;

	public ChangeSpaceUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		try{
		parkingSpacesLbl = new JLabel("Total Spaces: " + controller.getGarage().getTotalSpaces());
		mainContentPnl.add(parkingSpacesLbl);
		mainContentPnl.add(new JLabel("New # Spaces: "));
		mainContentPnl.add(spacesTxt);
		mainContentPnl.add(updateBtn);
		mainContentPnl.add(backBtn);
		updateBtn.addActionListener(new ChangeSpaceUIListener());
		backBtn.addActionListener(new ChangeSpaceUIListener());

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
		spacesTxt.setText("");
		parkingSpacesLbl.setText("Total Spaces: " + controller.getGarage().getTotalSpaces());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	private class ChangeSpaceUIListener implements ActionListener {
		ChangeSpaceUIListener() {
		}

		public void actionPerformed(ActionEvent e) {
			try{
			if (e.getActionCommand().equals("Update")) {
				int newSpaces;
				if(spacesTxt.getText().equals("")) newSpaces=controller.getGarage().getTotalSpaces();
				else newSpaces = Integer.parseInt(spacesTxt.getText());
				controller.getGarage().updateGarageSpaces(newSpaces);
				JOptionPane.showMessageDialog(mainUI, "Number of spaces updated.", "Message", JOptionPane.INFORMATION_MESSAGE);
				repaintUI();
			}
			if (e.getActionCommand().equals("Back")) {;
				mainUI.adminUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
				
			}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}