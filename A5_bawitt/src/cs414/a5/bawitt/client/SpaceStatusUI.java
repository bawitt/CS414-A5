package cs414.a5.bawitt.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cs414.a5.bawitt.common.Controller;

public class SpaceStatusUI {
	private MainUI mainUI;
	private Controller controller;
	JPanel mainContentPnl = new JPanel();
	private JPanel spacesPnl = new JPanel(new GridLayout(3,1));
	JLabel usedLbl = new JLabel();
	JLabel totalLbl = new JLabel();
	private JButton mainMenuBtn = new JButton("Main Menu");

	public SpaceStatusUI(MainUI mainUI, Controller controller) {
		this.mainUI = mainUI;
		this.controller = controller;
	}

	public void paintUI() {
		try {
		usedLbl = new JLabel("Used Spaces: " + controller.getGarage().getUsedSpaces());
		totalLbl = new JLabel("Total Spaces: " + controller.getGarage().getTotalSpaces());

		spacesPnl.add(usedLbl);
		spacesPnl.add(totalLbl);
		spacesPnl.add(mainMenuBtn);
		mainMenuBtn.addActionListener(new SpaceStatusUIListener());

		mainContentPnl.add(spacesPnl);
		
		mainContentPnl.setVisible(false);
		mainUI.mainPnl.add(mainContentPnl);
		
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void repaintUI() {
		try{
		spacesPnl.setVisible(true);
		mainMenuBtn.setVisible(true);		
		usedLbl.setText("Used Spaces: " + controller.getGarage().getUsedSpaces());
		totalLbl.setText("Total Spaces: " + controller.getGarage().getTotalSpaces());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private class SpaceStatusUIListener implements ActionListener {
		private SpaceStatusUIListener() {
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Main Menu")) {
				repaintUI();			
				mainUI.mainContentPnl.setVisible(true);
				mainContentPnl.setVisible(false);
				mainUI.updateStatus();
			}
		}
	}
}
