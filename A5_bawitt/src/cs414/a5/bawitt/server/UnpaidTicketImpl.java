package cs414.a5.bawitt.server;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import cs414.a5.bawitt.common.Ticket;
import cs414.a5.bawitt.common.UnpaidTicket;

public class UnpaidTicketImpl extends java.rmi.server.UnicastRemoteObject implements UnpaidTicket {

	private static final long serialVersionUID = 1L;
	private String custName;
	private String custAddress;
	private String custPhone;
	private Ticket unpaidTicket;
	private String unpaidTicketString;
	private String ticketID;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public UnpaidTicketImpl(String cn, String ca, String cp, Ticket up) throws java.rmi.RemoteException{
		custName = cn;
		custAddress = ca;
		custPhone = cp;
		unpaidTicket = up;
		if(unpaidTicket.getID()==-1) ticketID = "N/A";
		else ticketID = Integer.toString(unpaidTicket.getID());
	}

	@Override
	public String unpaidToString() throws java.rmi.RemoteException{
		try {
			unpaidTicketString = "\nName: " + custName + "\nAddress: " + custAddress 
					+ "\nPhone #: " + custPhone + "\nTicket ID: " + ticketID + "\nAmount Due: "
					+ df.format(unpaidTicket.getAmountDue()) + "\n";
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return unpaidTicketString;
	}
	
	@Override
	public Ticket getUnpaidTicket(){
		return unpaidTicket;
	}
}
