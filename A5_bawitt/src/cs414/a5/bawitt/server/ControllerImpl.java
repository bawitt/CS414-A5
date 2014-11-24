package cs414.a5.bawitt.server;

import java.rmi.RemoteException;

import org.joda.time.DateTime;

import cs414.a5.bawitt.common.Controller;
import cs414.a5.bawitt.common.Garage;
import cs414.a5.bawitt.common.Ticket;

public class ControllerImpl
//extends java.rmi.server.UnicastRemoteObject 
implements Controller {
	
	public ControllerImpl() throws RemoteException {
		super();
	}
	
	private Garage garage = new GarageImpl();
	
	@Override
	public Garage getGarage() throws java.rmi.RemoteException{
		return garage;
	}

	@Override
	public DateTime getTicketEnterDate(int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		return t.getEnterDate();
	}
	@Override
	public boolean isTicketValid(int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		if(t.getID()==-1) return false;
		else return true;
	}
	@Override
	public double getAmountDueByID(int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		return t.getAmountDue();
	}
	@Override
	public void exitWithoutPayment(String n, String a, String p, int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		UnpaidTicketImpl up = new UnpaidTicketImpl(n, a, p, t);
		garage.addUnpaidTicketToList(up); 
		garage.exitGarage(tid);
	}
	@Override
	public String makeCashPayment(int tid, double amount) throws java.rmi.RemoteException{
		CashPaymentImpl cp = new CashPaymentImpl(tid, amount);
		ReceiptImpl r = new ReceiptImpl(cp);
		garage.addReceiptToCollection(r);
		return r.getReceipt();
	}
	@Override
	public String makeElectronicPayment(double amountDue, String ccNum, String expDate, int ticketNumber) throws java.rmi.RemoteException{
		ElectronicPaymentImpl ep = new ElectronicPaymentImpl(amountDue, ccNum, expDate, ticketNumber);
		ReceiptImpl r = new ReceiptImpl(ep);
		garage.addReceiptToCollection(r);
		return r.getReceipt();
	}
}
