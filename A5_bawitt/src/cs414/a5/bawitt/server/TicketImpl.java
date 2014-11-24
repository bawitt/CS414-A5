package cs414.a5.bawitt.server;

import org.joda.time.*;
import cs414.a5.bawitt.common.Rate;
import cs414.a5.bawitt.common.Ticket;

public class TicketImpl extends java.rmi.server.UnicastRemoteObject implements Ticket {

	private static final long serialVersionUID = 1L;
	private int id;
	private DateTime enterDate;
	private double ticketStandardRate;
	private double ticketFlatRate;
	private DateTime paymentDate;
	private int duration;
	private int totalStayLength;
	
	public TicketImpl(int i, Rate r) throws java.rmi.RemoteException{
		id = i;
		enterDate = new DateTime();
		ticketStandardRate = r.getStandardRate();
	}
	public TicketImpl(Rate r) throws java.rmi.RemoteException{
		id = -1;
		ticketFlatRate = r.getFlatRate();
		totalStayLength = 0;
	}
	//hours from entry until current time
	@Override
	public int getDurationHours() throws java.rmi.RemoteException{  
		paymentDate = new DateTime();
		Hours hoursInGarage = Hours.hoursBetween(enterDate, paymentDate);
		totalStayLength = hoursInGarage.getHours() + 1;
		return totalStayLength;
	}

	@Override
	public int getID() throws java.rmi.RemoteException{
		return id;
	}

	@Override
	public DateTime getEnterDate() throws java.rmi.RemoteException{
		return enterDate;
	}

	//@Override
	public void setPaymentDate(DateTime dt) throws java.rmi.RemoteException{ //for junit testing purposes
		paymentDate = dt;
	}

	@Override
	public double getTicketStandardRate() throws java.rmi.RemoteException{
		return ticketStandardRate;
	}

	@Override
	public double getAmountDue() throws java.rmi.RemoteException{
		if(id==-1) return ticketFlatRate;
		duration = getDurationHours();
		return (duration * getTicketStandardRate());
	}
	
	@Override
	public int getTotalStayLength() throws java.rmi.RemoteException{
		return totalStayLength;
	}
}
