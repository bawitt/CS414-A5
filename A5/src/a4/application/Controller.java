package a4.application;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Controller {
	private GarageImpl garage = new Garage();
	private Scanner input = new Scanner(System.in);
	//private int choice;
	private int ticketID;
	//private Payment pay;
	private double amountDue;
	//private String userName;
	private DecimalFormat df = new DecimalFormat("0.00");
	private Ticket ticket;
	
	public void pause(){
		System.out.println("Press Enter to continue.");
		try{System.in.read();}
		catch(Exception e){}
	}
	
	public void mainMenu(){
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
	public void exitMenu(){
		int exitChoice = 0;
		//Ticket ticket = new Ticket();
		System.out.println("\nPlease Select an option: \n1. Enter Ticket ID "
				+ "\n2. Flat Rate Payment \n3. Back");
		exitChoice = input.nextInt();
		switch(exitChoice){
		case 1: 
			ticketID = getTicketID();
			ticket = garage.getTicketFromList(ticketID);
			if(ticket.getID()==-1) {System.out.println("\nInvalid ticket ID.\n"); exitMenu(); break;}
			amountDue = ticket.getAmountDue();
			System.out.println("\nAmount due: " + df.format(amountDue) + "\n"); 
			paymentMenu(amountDue, ticket, false); break;
		case 2:  
			Ticket ticket = new Ticket();
			amountDue = garage.getGarageFlatRate();
			System.out.println("\nAmount due: $" + df.format(amountDue) + "\n");
			paymentMenu(amountDue, ticket, true); break;
		case 3: break;
		default:
			System.out.println("Invalid selection.\n");
			exitMenu(); break;
		}
	}

	public void paymentMenu(double amountDue, Ticket ticket, boolean isFlatRate){
		int payChoice = 0;
		System.out.println("Please Select an option: \n1. Cash Payment "
				+ "\n2. Electronic Payment \n3. Back");
		payChoice = input.nextInt();
		switch(payChoice){
		case 1: 
			System.out.println("Please insert cash. Enter amount in USD: ");
			double paymentAmount = input.nextDouble();
			CashPayment cp = new CashPayment(amountDue, paymentAmount); 
			while(cp.getAmountDue()>0){
				System.out.println("Remaining balance: $"+ df.format(cp.getAmountDue())); 
				System.out.println("Please insert cash. Enter amount in USD: ");
				paymentAmount = input.nextDouble();
				cp.makePostCashPayment(paymentAmount);
				}
			System.out.println("Please take change: $" + df.format(cp.getChange())); 
			Receipt receipt = new Receipt(cp);
			receipt.printReceipt();
			garage.addReceiptToCollection(receipt);
			pause();
			if(isFlatRate==true)garage.exitGarage(); 
			if(isFlatRate==false)garage.exitGarage(ticket); 
			break;	
		case 2: 
			System.out.println("Please enter account number (16 digits no dashes): ");
			String accountNum = input.next();
			System.out.println("Please enter expiration date (format MM/yyyy): ");
			String expDate = input.next();
			ElectronicPayment ep = new ElectronicPayment(amountDue, accountNum, expDate);
			if(ep.isAccountValid()){
				Receipt r = new Receipt(ep);
				r.printReceipt();
				garage.addReceiptToCollection(r);
				pause();
				if(isFlatRate==true)garage.exitGarage(); 
				else garage.exitGarage(ticket); 
			}
			else paymentMenu(amountDue, ticket, isFlatRate);
			break;
		case 3: break;
		default:
			System.out.println("Invalid selection.\n");
			paymentMenu(amountDue, ticket, isFlatRate); break;
		}
	}
	
	public void empMenu(){
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
			EmployeeImpl emp = garage.getEmployeeFromList(userName);
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
			Ticket custTicket = garage.getTicketFromList(custTicketID);
			if(custTicket.getID()==-1) {System.out.println("\nInvalid ticket ID.\n"); empMenu();break;}
			UnpaidTicket up = new UnpaidTicket(custName, custAddress, custPhone, custTicket);
			garage.addUnpaidTicketToList(up); 
			garage.exitGarage(custTicket); break;
		case 7: 
			garage.showUnpaidTickets(); break;
		case 8: break;	
		default:
			System.out.println("Invalid selection.\n");
			empMenu(); break;
		}
	}
	public int getTicketID(){
		System.out.println("Enter ticket ID: ");
		ticketID = input.nextInt();
		return ticketID;
	}
	
	public static void main(String [ ] args){
		Controller garageController = new Controller();
		garageController.mainMenu();
	}
}
