package a4.application;

//import java.util.Date;
import org.joda.time.*;

public class Ticket implements TicketImpl {
	private int id;
	private DateTime enterDate;
	private double ticketStandardRate;
	private DateTime paymentDate;
	private int duration;
	
	public Ticket(int i, RateImpl r){
		id = i;
		enterDate = new DateTime();
		ticketStandardRate = r.getStandardRate();
	}
	public Ticket(){
		id = -1;
	}
	//hours from entry until current time
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getDurationHours()
	 */
	@Override
	public int getDurationHours(){  
		paymentDate = new DateTime();
		Hours hoursInGarage = Hours.hoursBetween(enterDate, paymentDate);
		return (hoursInGarage.getHours() + 1);
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getID()
	 */
	@Override
	public int getID(){
		return id;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getEnterDate()
	 */
	@Override
	public DateTime getEnterDate(){
		return enterDate;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#setPaymentDate(org.joda.time.DateTime)
	 */
	@Override
	public void setPaymentDate(DateTime dt){ //for testing purposes
		paymentDate = dt;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getTicketStandardRate()
	 */
	@Override
	public double getTicketStandardRate(){
		return ticketStandardRate;
	}
	/* (non-Javadoc)
	 * @see a4.application.TicketImpl#getAmountDue()
	 */
	@Override
	public double getAmountDue(){
		duration = getDurationHours();
		return (duration * getTicketStandardRate());
	}
}
