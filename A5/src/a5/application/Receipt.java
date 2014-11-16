package a5.application;

import java.text.DecimalFormat;
import java.util.Date;

public class Receipt implements ReceiptImpl {
	private Date paymentDate;
	private PaymentType paymentType;
	private double amount;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public Receipt(PaymentImpl pay){
		paymentDate = pay.getPaymentDate();
		amount = pay.getOriginalAmountDue();
		paymentType = pay.getPaymentType();
	}
	
	/* (non-Javadoc)
	 * @see a4.application.ReceiptImpl#printReceipt()
	 */
	@Override
	public void printReceipt(){
		System.out.println("\nReceipt: \nThank you for your business.\nDate: "+paymentDate+
				"\nTotal: $" + df.format(amount) + "\nPayment Type: " + paymentType + "\n");
	}
}
