package cs414.a5.bawitt.server;

import java.util.*;

import cs414.a5.bawitt.common.Employee;
import cs414.a5.bawitt.common.Garage;
import cs414.a5.bawitt.common.Gate;
import cs414.a5.bawitt.common.Rate;
import cs414.a5.bawitt.common.Receipt;
import cs414.a5.bawitt.common.Sign;
import cs414.a5.bawitt.common.Spaces;
import cs414.a5.bawitt.common.Ticket;
import cs414.a5.bawitt.common.UnpaidTicket;

public class GarageImpl extends java.rmi.server.UnicastRemoteObject  implements Garage {

	private static final long serialVersionUID = 1L;
	
	private Sign capacitySign;
	private Gate entryGate;
	private Gate exitGate;
	private Rate garageRate;
	private Spaces garageSpaces;
	private int ticketCount;
	private Employee emp;
	private Set<TicketImpl> activeTickets = new HashSet<TicketImpl>();
	private Set<TicketImpl> paidTickets = new HashSet<TicketImpl>();
	private Set<UnpaidTicketImpl> unpaidTickets = new HashSet<UnpaidTicketImpl>();
	private Set<EmployeeImpl> users = new HashSet<EmployeeImpl>();
	private Set<ReceiptImpl> receipts = new HashSet<ReceiptImpl>();
	
	public GarageImpl() throws java.rmi.RemoteException{ //hard values exist for initial object creation
		super();
		createEmployee("bwitt", "1234");
		capacitySign = new SignImpl();
		entryGate = new GateImpl(GateTypeImpl.entrance);
		exitGate = new GateImpl(GateTypeImpl.exit);
		garageRate = new RateImpl(4, 50);  
		garageSpaces = new SpacesImpl(5,0);  
		ticketCount = 0;
	}
	
	@Override
	public int getTotalSpaces() throws java.rmi.RemoteException
	{
		return garageSpaces.getNumSpaces();
	}
	
	@Override
	public int getUsedSpaces() throws java.rmi.RemoteException
	{
		return garageSpaces.getUsedSpaces();
	}

	@Override
	public void updateSignStatus() throws java.rmi.RemoteException{
		capacitySign.refreshSign(garageSpaces);
	}

	@Override
	public void updateGarageSpaces(int numSpaces) throws java.rmi.RemoteException{
		garageSpaces.setNumSpaces(numSpaces); 
		updateSignStatus();
	}

	@Override
	public void updateGarageStandardRate(double r) throws java.rmi.RemoteException{
		garageRate.setStandardRate(r);
	}

	@Override
	public void updateGarageFlatRate(double r) throws java.rmi.RemoteException{
		garageRate.setFlatRate(r);
	}

	@Override
	public int issueTicket() throws java.rmi.RemoteException{
			ticketCount = ticketCount +1;
			TicketImpl ticket = new TicketImpl(ticketCount, garageRate);
			garageSpaces.incEnter();
			activeTickets.add(ticket);
			entryGate.openGate();
			entryGate.closeGate();
			updateSignStatus();
			return ticket.getID();
	}

	@Override
	public TicketImpl getTicketFromList(int id) throws java.rmi.RemoteException{
		for(TicketImpl ticket : activeTickets){
			if(ticket.getID()==id){
				return ticket;
			}
		}
		TicketImpl ticket= new TicketImpl(garageRate);
		return ticket;
	}

	@Override
	public Employee getEmployeeFromList(String un) throws java.rmi.RemoteException{
		for(Employee emp : users){
			if(emp.getUsername().equals(un)){
				return emp;
			}
		}
		Employee emp = new EmployeeImpl();
		return emp;
	}

	@Override
	public boolean authorizeUser(String un, String pn) throws java.rmi.RemoteException{
		boolean success = false;
		emp = getEmployeeFromList(un);
		success = emp.isApproved(pn);
		return success;
	}

	@Override
	public void exitGarage(int tid) throws java.rmi.RemoteException{
		if(tid<=0) {exitGarage(); return;}
		TicketImpl ticket = getTicketFromList(tid);
		garageSpaces.decExit();	
		exitGate.openGate();
		exitGate.closeGate();
		activeTickets.remove(ticket);
		paidTickets.add(ticket);
		updateSignStatus();
	}

	@Override
	public void exitGarage() throws java.rmi.RemoteException{
		garageSpaces.decExit();	
		exitGate.openGate();
		exitGate.closeGate();
		updateSignStatus();
	}

	@Override
	public boolean createEmployee(String un, String pn) throws java.rmi.RemoteException{
		for(Employee e : users)
		{
			if(e.getUsername().equals(un)) return false;
		}
		EmployeeImpl emp = new EmployeeImpl(un, pn);
		users.add(emp);
		return true;
	}

	@Override
	public Rate getGarageRate() throws java.rmi.RemoteException{
		return garageRate;
	}

	@Override
	public SignStatusImpl getSignStatus() throws java.rmi.RemoteException{
		return capacitySign.getSignStatus();
	}

	@Override
	public double getGarageFlatRate() throws java.rmi.RemoteException{
		return garageRate.getFlatRate();
	}

	@Override
	public Set<EmployeeImpl> getEmployeeList() throws java.rmi.RemoteException{
		return users;
	}

	@Override
	public Set<TicketImpl> getActiveTicketList() throws java.rmi.RemoteException{
		return activeTickets;
	}

	@Override
	public String showUnpaidTickets() throws java.rmi.RemoteException{
		String utString = "Unpaid List:";
		for(UnpaidTicket ut : unpaidTickets){
			utString += ut.unpaidToString();
		}
		return utString;
	}
	
	@Override
	public Set<TicketImpl> getPaidTicketList() throws java.rmi.RemoteException{
		return paidTickets;
	}

	@Override
	public int getGarageUsedSpaces() throws java.rmi.RemoteException{
		return garageSpaces.getUsedSpaces();
	}

	@Override
	public void addReceiptToCollection(ReceiptImpl r) throws java.rmi.RemoteException{
		receipts.add(r);
	}

	@Override
	public void addUnpaidTicketToList(UnpaidTicketImpl ut) throws java.rmi.RemoteException{
		unpaidTickets.add(ut);
	}

	@Override
	public void showsSpaceStatus() throws java.rmi.RemoteException{
		System.out.println("Status: " + capacitySign.getSignStatus()
				+ "\nTotal Spaces: " + garageSpaces.getNumSpaces()
				+ "\nUsed Spaces: " + garageSpaces.getUsedSpaces() + "\n");
	}	
	
	@Override
	public double getAmountOwedToGarage() throws java.rmi.RemoteException{
		double amount = 0;
		for(UnpaidTicket ut : unpaidTickets){
			amount += ut.getUnpaidTicket().getAmountDue();
		}
		for(TicketImpl ticket : activeTickets){
			amount+= ticket.getAmountDue();
		}
		return amount;
	}
	
	@Override
	public double getAverageLengthOfStay() throws java.rmi.RemoteException{
		int length = 0;
		int count = 0;
		for(Ticket t : paidTickets){
			if(t.getTotalStayLength()>0){
				length += t.getTotalStayLength();
				count++;
			}
		}
		if(count==0) return 0;
		else return length/count;
	}
	
	@Override
	public double getTotalRevenue() throws java.rmi.RemoteException{
		double amount = 0;
		for(Receipt r : receipts){
			amount += r.getAmount();
		}
		return amount;
	}
}
