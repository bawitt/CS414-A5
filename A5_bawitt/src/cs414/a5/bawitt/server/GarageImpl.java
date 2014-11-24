package cs414.a5.bawitt.server;

//import org.joda.time.DateTime;
import java.rmi.server.UnicastRemoteObject;
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
		garageSpaces = new SpacesImpl(20,0);  
		ticketCount = 0;
	}
	
	public int getTotalSpaces() throws java.rmi.RemoteException
	{
		return garageSpaces.getNumSpaces();
	}
	public int getUsedSpaces() throws java.rmi.RemoteException
	{
		return garageSpaces.getUsedSpaces();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateSignStatus()
	 */
	@Override
	public void updateSignStatus() throws java.rmi.RemoteException{
		capacitySign.refreshSign(garageSpaces);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateGarageSpaces(int)
	 */
	@Override
	public void updateGarageSpaces(int numSpaces) throws java.rmi.RemoteException{
		garageSpaces.setNumSpaces(numSpaces); 
		updateSignStatus();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateGarageStandardRate(double)
	 */
	@Override
	public void updateGarageStandardRate(double r) throws java.rmi.RemoteException{
		garageRate.setStandardRate(r);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateGarageFlatRate(double)
	 */
	@Override
	public void updateGarageFlatRate(double r) throws java.rmi.RemoteException{
		garageRate.setFlatRate(r);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#issueTicket()
	 */
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
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getTicketFromList(int)
	 */
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
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getEmployeeFromList(java.lang.String)
	 */
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
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#authorizeUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authorizeUser(String un, String pn) throws java.rmi.RemoteException{
		boolean success = false;
		emp = getEmployeeFromList(un);
		success = emp.isApproved(pn);
		return success;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#exitGarage(a4.application.Ticket)
	 */
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
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#exitGarage()
	 */
	@Override
	public void exitGarage() throws java.rmi.RemoteException{
		garageSpaces.decExit();	
		exitGate.openGate();
		exitGate.closeGate();
		updateSignStatus();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#createEmployee(java.lang.String, java.lang.String)
	 */
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
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getGarageRate()
	 */
	@Override
	public Rate getGarageRate() throws java.rmi.RemoteException{
		return garageRate;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getSignStatus()
	 */
	@Override
	public SignStatusImpl getSignStatus() throws java.rmi.RemoteException{
		return capacitySign.getSignStatus();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getGarageFlatRate()
	 */
	@Override
	public double getGarageFlatRate() throws java.rmi.RemoteException{
		return garageRate.getFlatRate();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getEmployeeList()
	 */
	@Override
	public Set<EmployeeImpl> getEmployeeList() throws java.rmi.RemoteException{
		return users;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getActiveTicketList()
	 */
	@Override
	public Set<TicketImpl> getActiveTicketList() throws java.rmi.RemoteException{
		return activeTickets;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#showUnpaidTickets()
	 */
	@Override
	public String showUnpaidTickets() throws java.rmi.RemoteException{
		String utString = "Unpaid List:";
		for(UnpaidTicket ut : unpaidTickets){
			utString += ut.unpaidToString();
		}
		return utString;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getPaidTicketList()
	 */
	@Override
	public Set<TicketImpl> getPaidTicketList() throws java.rmi.RemoteException{
		return paidTickets;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getGarageUsedSpaces()
	 */
	@Override
	public int getGarageUsedSpaces() throws java.rmi.RemoteException{
		return garageSpaces.getUsedSpaces();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#addReceiptToCollection(a4.application.Receipt)
	 */
	@Override
	public void addReceiptToCollection(ReceiptImpl r) throws java.rmi.RemoteException{
		receipts.add(r);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#addUnpaidTicketToList(a4.application.UnpaidTickets)
	 */
	@Override
	public void addUnpaidTicketToList(UnpaidTicketImpl ut) throws java.rmi.RemoteException{
		unpaidTickets.add(ut);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#showsSpaceStatus()
	 */
	@Override
	public void showsSpaceStatus() throws java.rmi.RemoteException{
		System.out.println("Status: " + capacitySign.getSignStatus()
				+ "\nTotal Spaces: " + garageSpaces.getNumSpaces()
				+ "\nUsed Spaces: " + garageSpaces.getUsedSpaces() + "\n");
	}	
	
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
	
	public double getTotalRevenue() throws java.rmi.RemoteException{
		double amount = 0;
		for(Receipt r : receipts){
			amount += r.getAmount();
		}
		return amount;
	}
}
