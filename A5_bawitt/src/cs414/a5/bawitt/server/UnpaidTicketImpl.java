package cs414.a5.bawitt.server;

import java.rmi.RemoteException;

import cs414.a5.bawitt.common.Ticket;
import cs414.a5.bawitt.common.UnpaidTicket;


public class UnpaidTicketImpl extends java.rmi.server.UnicastRemoteObject implements UnpaidTicket {
	private String custName;
	private String custAddress;
	private String custPhone;
	private Ticket unpaidTicket;
	private String unpaidTicketString;
	
	public UnpaidTicketImpl(String cn, String ca, String cp, Ticket up) throws java.rmi.RemoteException{
		custName = cn;
		custAddress = ca;
		custPhone = cp;
		unpaidTicket = up;
	}
	/* (non-Javadoc)
	 * @see a4.application.UnpaidTicketsImpl#toString()
	 */
	@Override
	public String unpaidToString() throws java.rmi.RemoteException{
		try {
			unpaidTicketString = "\nName: " + custName + "\nAddress: " + custAddress 
					+ "\nPhone #: " + custPhone + "\nTicket ID: " + unpaidTicket.getID() + "\n";
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return unpaidTicketString;
	}
}
