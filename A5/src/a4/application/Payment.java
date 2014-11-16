package a4.application;

//import org.joda.time.*;
import java.util.Date;
//import java.util.Calendar;

public class Payment implements PaymentImpl {
	private PaymentType paymentType;
	private double amountDue;
	private double originalAmountDue;
	private Date paymentDate;
	
	public Payment(double ad, PaymentType pt){
		paymentDate = new Date();
		paymentType = pt;
		originalAmountDue = ad;
		amountDue = ad;
	}

	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getAmountDue()
	 */
	@Override
	public double getAmountDue(){
		return amountDue;
	}
	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getPaymentDate()
	 */
	@Override
	public Date getPaymentDate(){
		return paymentDate;
	}
	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getPaymentType()
	 */
	@Override
	public PaymentType getPaymentType(){
		return paymentType;
	}
	/* (non-Javadoc)
	 * @see a4.application.PaymentImpl#getOriginalAmountDue()
	 */
	@Override
	public double getOriginalAmountDue(){
		return originalAmountDue;
	}
}
