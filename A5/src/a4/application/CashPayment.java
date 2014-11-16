package a4.application;

public class CashPayment extends Payment implements CashPaymentImpl{
	private double amountDue;
	private double change;
	private double paymentAmount;
	
	public CashPayment(double ad, double pa){
		super(ad, PaymentType.cash);
		amountDue = ad;
		paymentAmount = pa;
		change = 0;
		makeInitialCashPayment();	
	}
	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#makeInitialCashPayment()
	 */
	@Override
	public void makeInitialCashPayment(){
		amountDue = amountDue - paymentAmount;
		if(amountDue<0) change = amountDue *-1;
	}
	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#makePostCashPayment(double)
	 */
	@Override
	public void makePostCashPayment(double paymentAmount){
		amountDue = amountDue - paymentAmount;
		if(amountDue<0) change = amountDue *-1;
	}
	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#getAmountDue()
	 */
	@Override
	public double getAmountDue(){
		return amountDue;
	}
	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#getChange()
	 */
	@Override
	public double getChange(){
		return change;
	}
}
