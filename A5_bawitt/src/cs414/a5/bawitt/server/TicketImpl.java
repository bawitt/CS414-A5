package cs414.a5.bawitt.server;

//import java.util.Date;
import org.joda.time.*;

import cs414.a5.bawitt.common.Rate;
import cs414.a5.bawitt.common.Ticket;

public class TicketImpl extends java.rmi.server.UnicastRemoteObject implements Ticket {

	private static final long serialVersionUID = 1L;
	private int id;
	private DateTime enterDate;
	private double ticketStandardRate;
	private DateTime paymentDate;
	private int duration;
	
	public TicketImpl(int i, Rate r) throws java.rmi.RemoteException{
		id = i;
		enterDate = new DateTime();
		ticketStandardRate = r.getStandardRate();
	}
	public TicketImpl() throws java.rmi.RemoteException{
		id = -1;
	}
	//hours from entry until current time
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getDurationHours()
	 */
	@Override
	public int getDurationHours() throws java.rmi.RemoteException{  
		paymentDate = new DateTime();
		Hours hoursInGarage = Hours.hoursBetween(enterDate, paymentDate);
		return (hoursInGarage.getHours() + 1);
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getID()
	 */
	@Override
	public int getID() throws java.rmi.RemoteException{
		return id;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getEnterDate()
	 */
	@Override
	public DateTime getEnterDate() throws java.rmi.RemoteException{
		return enterDate;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#setPaymentDate(org.joda.time.DateTime)
	 */
	@Override
	public void setPaymentDate(DateTime dt) throws java.rmi.RemoteException{ //for testing purposes
		paymentDate = dt;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getTicketStandardRate()
	 */
	@Override
	public double getTicketStandardRate() throws java.rmi.RemoteException{
		return ticketStandardRate;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getAmountDue()
	 */
	@Override
	public double getAmountDue() throws java.rmi.RemoteException{
		duration = getDurationHours();
		return (duration * getTicketStandardRate());
	}
}
