package a4.application;

//import org.joda.time.DateTime;
import java.util.*;

public class Garage implements GarageImpl {
	private SignImpl capacitySign;
	private GateImpl entryGate;
	private GateImpl exitGate;
	private RateImpl garageRate;
	private SpacesImpl garageSpaces;
	private int ticketCount;
	private EmployeeImpl emp;
	private Set<Ticket> activeTickets = new HashSet<Ticket>();
	private Set<Ticket> paidTickets = new HashSet<Ticket>();
	private Set<UnpaidTicket> unpaidTickets = new HashSet<UnpaidTicket>();
	private Set<Employee> users = new HashSet<Employee>();
	private Set<Receipt> receipts = new HashSet<Receipt>();
	
	public Garage(){ //hard values exist for initial object creation
		createEmployee("bwitt", "1234");
		capacitySign = new Sign();
		entryGate = new Gate(GateType.entrance);
		exitGate = new Gate(GateType.exit);
		garageRate = new Rate(4, 50);  
		garageSpaces = new Spaces(20,0);  
		ticketCount = 0;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateSignStatus()
	 */
	@Override
	public void updateSignStatus(){
		capacitySign.refreshSign(garageSpaces);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateGarageSpaces(int)
	 */
	@Override
	public void updateGarageSpaces(int numSpaces){
		garageSpaces.setNumSpaces(numSpaces); 
		updateSignStatus();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateGarageStandardRate(double)
	 */
	@Override
	public void updateGarageStandardRate(double r){
		garageRate.setStandardRate(r);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#updateGarageFlatRate(double)
	 */
	@Override
	public void updateGarageFlatRate(double r){
		garageRate.setFlatRate(r);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#issueTicket()
	 */
	@Override
	public void issueTicket() {
		if(capacitySign.getSignStatus()==SignStatus.vacancy){ //prohibit entrance when full
			ticketCount = ticketCount +1;
			Ticket ticket = new Ticket(ticketCount, garageRate);
			garageSpaces.incEnter();
			activeTickets.add(ticket);
			System.out.println("Your ticket ID: "+ ticket.getID() 
					+ "\nEntrance date/time: "+ ticket.getEnterDate() +"\n");
			entryGate.openGate();
			try {Thread.sleep(1500);} catch (InterruptedException e) {e.printStackTrace();}
			entryGate.closeGate();
			updateSignStatus();
		}
		else {
			System.out.println("Garage is currently full");
		}
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getTicketFromList(int)
	 */
	@Override
	public Ticket getTicketFromList(int id){
		for(Ticket ticket : activeTickets){
			if(ticket.getID()==id){
				return ticket;
			}
		}
		Ticket ticket= new Ticket();
		return ticket;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getEmployeeFromList(java.lang.String)
	 */
	@Override
	public EmployeeImpl getEmployeeFromList(String un){
		for(EmployeeImpl emp : users){
			if(emp.getUsername().equals(un)){
				return emp;
			}
		}
		EmployeeImpl emp = new Employee();
		return emp;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#authorizeUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authorizeUser(String un, String pn){
		boolean success = false;
		emp = getEmployeeFromList(un);
		success = emp.isApproved(pn);
		return success;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#exitGarage(a4.application.Ticket)
	 */
	@Override
	public void exitGarage(Ticket ticket){
		garageSpaces.decExit();	
		exitGate.openGate();
		try {Thread.sleep(1500);} catch (InterruptedException e) {e.printStackTrace();}
		exitGate.closeGate();
		activeTickets.remove(ticket);
		paidTickets.add(ticket);
		updateSignStatus();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#exitGarage()
	 */
	@Override
	public void exitGarage(){
		garageSpaces.decExit();	
		exitGate.openGate();
		try {Thread.sleep(1500);} catch (InterruptedException e) {e.printStackTrace();}
		exitGate.closeGate();
		updateSignStatus();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#createEmployee(java.lang.String, java.lang.String)
	 */
	@Override
	public void createEmployee(String un, String pn){
		Employee emp = new Employee(un, pn);
		users.add(emp);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getGarageRate()
	 */
	@Override
	public RateImpl getGarageRate(){
		return garageRate;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getSignStatus()
	 */
	@Override
	public SignStatus getSignStatus(){
		return capacitySign.getSignStatus();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getGarageFlatRate()
	 */
	@Override
	public double getGarageFlatRate(){
		return garageRate.getFlatRate();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getEmployeeList()
	 */
	@Override
	public Set<Employee> getEmployeeList(){
		return users;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getActiveTicketList()
	 */
	@Override
	public Set<Ticket> getActiveTicketList(){
		return activeTickets;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#showUnpaidTickets()
	 */
	@Override
	public void showUnpaidTickets(){
		for(UnpaidTicketImpl ut : unpaidTickets){
			System.out.println(ut.toString());
		}
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getPaidTicketList()
	 */
	@Override
	public Set<Ticket> getPaidTicketList(){
		return paidTickets;
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#getGarageUsedSpaces()
	 */
	@Override
	public int getGarageUsedSpaces(){
		return garageSpaces.getUsedSpaces();
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#addReceiptToCollection(a4.application.Receipt)
	 */
	@Override
	public void addReceiptToCollection(Receipt r){
		receipts.add(r);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#addUnpaidTicketToList(a4.application.UnpaidTickets)
	 */
	@Override
	public void addUnpaidTicketToList(UnpaidTicket ut){
		unpaidTickets.add(ut);
	}
	/* (non-Javadoc)
	 * @see a4.application.GarageImpl#showsSpaceStatus()
	 */
	@Override
	public void showsSpaceStatus(){
		System.out.println("Status: " + capacitySign.getSignStatus()
				+ "\nTotal Spaces: " + garageSpaces.getNumSpaces()
				+ "\nUsed Spaces: " + garageSpaces.getUsedSpaces() + "\n");
	}	
}
