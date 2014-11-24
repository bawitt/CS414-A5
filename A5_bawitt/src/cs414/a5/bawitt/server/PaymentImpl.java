package cs414.a5.bawitt.server;

//import org.joda.time.*;
import java.util.Date;
//import java.util.Calendar;





import cs414.a5.bawitt.common.Payment;

public class  PaymentImpl extends java.rmi.server.UnicastRemoteObject implements Payment {
	private PaymentTypeImpl paymentType;
	private double amountDue;
	private double originalAmountDue;
	private Date paymentDate;
	private String ticketID;
	
	public PaymentImpl(double ad, PaymentTypeImpl pt, int t) throws java.rmi.RemoteException{
		paymentDate = new Date();
		paymentType = pt;
		originalAmountDue = ad;
		amountDue = ad;
		if(t==0) ticketID = "N/A";
		else ticketID = Integer.toString(t);
	}

	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getAmountDue()
	 */
	@Override
	public double getAmountDue() throws java.rmi.RemoteException{
		return amountDue;
	}
	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getPaymentDate()
	 */
	@Override
	public Date getPaymentDate() throws java.rmi.RemoteException{
		return paymentDate;
	}
	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getPaymentType()
	 */
	@Override
	public PaymentTypeImpl getPaymentType() throws java.rmi.RemoteException{
		return paymentType;
	}
	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getOriginalAmountDue()
	 */
	@Override
	public double getOriginalAmountDue() throws java.rmi.RemoteException{
		return originalAmountDue;
	}
	@Override
	public String getTicketID() throws java.rmi.RemoteException{
		return ticketID;
	}
}
