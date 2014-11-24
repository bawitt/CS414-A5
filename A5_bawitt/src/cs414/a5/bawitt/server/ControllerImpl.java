package cs414.a5.bawitt.server;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;

import org.joda.time.DateTime;

import cs414.a5.bawitt.client.MainUI;
import cs414.a5.bawitt.common.Controller;
import cs414.a5.bawitt.common.Garage;
import cs414.a5.bawitt.common.Ticket;

public class ControllerImpl
//extends java.rmi.server.UnicastRemoteObject 
implements Controller {
	
	public ControllerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private Garage garage = new GarageImpl();
	private Scanner input = new Scanner(System.in);
	private int ticketID;
	//private double amountDue;
	//private DecimalFormat df = new DecimalFormat("0.00");
	//private TicketImpl ticket;
	
	@Override
	//public void pause(){
	//	System.out.println("Press Enter to continue.");
	//	try{System.in.read();}
	//	catch(Exception e){}
	//}
	public Garage getGarage() throws java.rmi.RemoteException{
		return garage;
	}
	/*
	@Override
	public void mainMenu() throws java.rmi.RemoteException{
		int mainChoice=0;	
		while (mainChoice !=6){
		System.out.println("Please Select an option: \n1. Enter Garage "
				+ "\n2. Exit Garage \n3. Request Assistance \n4. Employee Login "
				+ "\n5. Capacity Status \n6. Quit");
		mainChoice = input.nextInt();
		switch(mainChoice){
		case 1: 
			String enterChoice = null;
			System.out.println("Press 1 to receive ticket and enter or 2 to refuse: ");
			enterChoice = input.next();
			System.out.println(enterChoice);
			if(enterChoice.equals("1")){garage.issueTicket(); break;}
			else {System.out.println("Thank you. Please exit."); break;}
		case 2: exitMenu(); break;
		case 3: System.out.println("Assistance requested"); break;
		case 4: 			
			System.out.println("\nEnter user name: ");
			String un = input.next(); 
			System.out.println("Enter user pass code: ");
			String pn = input.next();
			boolean loginSuccess = garage.authorizeUser(un, pn);
			if(loginSuccess) {System.out.println("\nSuccessful login.\n"); empMenu();}
			else {System.out.println("\nLogin unsuccessful.\n");}
			break;
		case 5: garage.showsSpaceStatus(); break;
		case 6: System.out.println("Exiting Application..."); break;
		default:
			System.out.println("Invalid selection.\n"); break;
		}
		pause();
		}
	}

	@Override
	public void exitMenu() throws java.rmi.RemoteException{
		int exitChoice = 0;
		//Ticket ticket = new Ticket();
		System.out.println("\nPlease Select an option: \n1. Enter Ticket ID "
				+ "\n2. Flat Rate Payment \n3. Back");
		exitChoice = input.nextInt();
		switch(exitChoice){
		case 1: 
			ticketID = getTicketID();
			//ticket = garage.getTicketFromList(ticketID);
			if(ticket.getID()==-1) {System.out.println("\nInvalid ticket ID.\n"); exitMenu(); break;}
			amountDue = ticket.getAmountDue();
			System.out.println("\nAmount due: " + df.format(amountDue) + "\n"); 
			paymentMenu(amountDue, ticket, false); break;
		case 2:  
			TicketImpl ticket = new TicketImpl();
			amountDue = garage.getGarageFlatRate();
			System.out.println("\nAmount due: $" + df.format(amountDue) + "\n");
			paymentMenu(amountDue, ticket, true); break;
		case 3: break;
		default:
			System.out.println("Invalid selection.\n");
			exitMenu(); break;
		}
	}

	@Override
	public void paymentMenu(double amountDue, TicketImpl ticket, boolean isFlatRate)throws java.rmi.RemoteException{
		int payChoice = 0;
		System.out.println("Please Select an option: \n1. Cash Payment "
				+ "\n2. Electronic Payment \n3. Back");
		payChoice = input.nextInt();
		switch(payChoice){
		case 1: 
			System.out.println("Please insert cash. Enter amount in USD: ");
			double paymentAmount = input.nextDouble();
			CashPaymentImpl cp = new CashPaymentImpl(amountDue, paymentAmount); 
			while(cp.getAmountDue()>0){
				System.out.println("Remaining balance: $"+ df.format(cp.getAmountDue())); 
				System.out.println("Please insert cash. Enter amount in USD: ");
				paymentAmount = input.nextDouble();
				cp.makePostCashPayment(paymentAmount);
				}
			System.out.println("Please take change: $" + df.format(cp.getChange())); 
			ReceiptImpl receipt = new ReceiptImpl(cp);
			receipt.printReceipt();
			garage.addReceiptToCollection(receipt);
			pause();
			if(isFlatRate==true)garage.exitGarage(); 
			//if(isFlatRate==false)garage.exitGarage(ticket); 
			break;	
		case 2: 
			System.out.println("Please enter account number (16 digits no dashes): ");
			String accountNum = input.next();
			System.out.println("Please enter expiration date (format MM/yyyy): ");
			String expDate = input.next();
			ElectronicPaymentImpl ep = new ElectronicPaymentImpl(amountDue, accountNum, expDate);
			if(ep.isAccountValid()){
				ReceiptImpl r = new ReceiptImpl(ep);
				r.printReceipt();
				garage.addReceiptToCollection(r);
				pause();
				if(isFlatRate==true)garage.exitGarage(); 
				//else garage.exitGarage(ticket); 
			}
			else paymentMenu(amountDue, ticket, isFlatRate);
			break;
		case 3: break;
		default:
			System.out.println("Invalid selection.\n");
			paymentMenu(amountDue, ticket, isFlatRate); break;
		}
	}
	
	@Override
	public void empMenu() throws java.rmi.RemoteException{
		int empChoice=0;	
		String userName = null;
		System.out.println("Please Select an option: \n1. Update Hourly Rate "
				+ "\n2. Update Flat Rate \n3. Update Spaces \n4. Create New User "
				+ "\n5. Deactivate User \n6. Override Payment on Exit \n7. Show Unpaid Tickets"
				+ "\n8. Back");
		empChoice = input.nextInt();
		switch(empChoice){
		case 1: 
			System.out.println("Enter new hourly rate: ");
			double newHourlyRate = input.nextDouble();
			garage.updateGarageStandardRate(newHourlyRate);break;
		case 2: 
			System.out.println("Enter new flat rate: ");
			double newFlatRate = input.nextDouble();
			garage.updateGarageFlatRate(newFlatRate);break;
		case 3: 
			System.out.println("Enter new number of total spaces: ");
			int newNumSpaces = input.nextInt();
			garage.updateGarageSpaces(newNumSpaces);break;
		case 4: 
			System.out.println("\nEnter new user name: ");
			userName = input.next();
			System.out.println("Enter new user pass code using numeric value: ");
			String pwChoice = input.next();
			garage.createEmployee(userName, pwChoice); break;
		case 5: 
			System.out.println("Enter user name to deactivate: ");
			userName = input.next();
			Employee emp = garage.getEmployeeFromList(userName);
			emp.deactivateUser(); break;
		case 6: 
			String custName, custAddress, custPhone;
			int custTicketID = -1;
			input.nextLine();
			System.out.println("Enter customer name: ");
			custName = input.nextLine();
			System.out.println("Enter customer address: ");
			custAddress = input.nextLine();
			System.out.println("Enter customer phone #: ");
			custPhone = input.nextLine();
			System.out.println("Enter customer ticket ID: ");
			custTicketID = input.nextInt();
			//TicketImpl custTicket = garage.getTicketFromList(custTicketID);
			//if(custTicket.getID()==-1) {System.out.println("\nInvalid ticket ID.\n"); empMenu();break;}
			//UnpaidTicketImpl up = new UnpaidTicketImpl(custName, custAddress, custPhone, custTicket);
			//garage.addUnpaidTicketToList(up); 
			//garage.exitGarage(custTicket); break;
		case 7: 
			garage.showUnpaidTickets(); break;
		case 8: break;	
		default:
			System.out.println("Invalid selection.\n");
			empMenu(); break;
		}
	}
*/
	@Override
	public int getTicketID() throws java.rmi.RemoteException{
		System.out.println("Enter ticket ID: ");
		ticketID = input.nextInt();
		return ticketID;
	}
	public DateTime getTicketEnterDate(int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		return t.getEnterDate();
	}
	public boolean isTicketValid(int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		if(t.getID()==-1) return false;
		else return true;
	}
	public double getAmountDueByID(int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		return t.getAmountDue();
	}
	public void exitWithoutTicket(String n, String a, String p, int tid) throws java.rmi.RemoteException{
		Ticket t = garage.getTicketFromList(tid);
		UnpaidTicketImpl up = new UnpaidTicketImpl(n, a, p, t);
		garage.addUnpaidTicketToList(up); 
		garage.exitGarage(tid);
	}
	public String makeCashPayment(int tid, double amount) throws java.rmi.RemoteException{
		CashPaymentImpl cp = new CashPaymentImpl(tid, amount);
		ReceiptImpl r = new ReceiptImpl(cp);
		garage.addReceiptToCollection(r);
		return r.getReceipt();
	}
	public String makeElectronicPayment(double amountDue, String ccNum, String expDate, int ticketNumber) throws java.rmi.RemoteException{
		ElectronicPaymentImpl ep = new ElectronicPaymentImpl(amountDue, ccNum, expDate, ticketNumber);
		ReceiptImpl r = new ReceiptImpl(ep);
		garage.addReceiptToCollection(r);
		return r.getReceipt();
	}
}
