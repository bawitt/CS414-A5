package cs414.a5.bawitt.server;

import java.text.DecimalFormat;
import java.util.Date;

import cs414.a5.bawitt.common.Payment;
import cs414.a5.bawitt.common.Receipt;

public class ReceiptImpl extends java.rmi.server.UnicastRemoteObject implements Receipt {

	private static final long serialVersionUID = 1L;
	private Date paymentDate;
	private PaymentTypeImpl paymentType;
	private double amount;
	private String ticketID;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public ReceiptImpl(Payment pay) throws java.rmi.RemoteException{
		paymentDate = pay.getPaymentDate();
		amount = pay.getOriginalAmountDue();
		paymentType = pay.getPaymentType();
		ticketID = pay.getTicketID();
	}
	
	@Override
	public String getReceipt() throws java.rmi.RemoteException{
		return "Thank you for your business.\nTicket: "+ticketID + "\nDate: " +paymentDate+
				"\nTotal: $" + df.format(amount) + "\nPayment Type: " + paymentType;
	}
}
