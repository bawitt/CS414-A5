package a4.application;

public class UnpaidTicket implements UnpaidTicketImpl {
	private String custName;
	private String custAddress;
	private String custPhone;
	private TicketImpl unpaidTicket;
	private String unpaidTicketString;
	
	public UnpaidTicket(String cn, String ca, String cp, TicketImpl up){
		custName = cn;
		custAddress = ca;
		custPhone = cp;
		unpaidTicket = up;
	}
	/* (non-Javadoc)
	 * @see a4.application.UnpaidTicketsImpl#toString()
	 */
	@Override
	public String toString(){
		unpaidTicketString = "\nName: " + custName + "\nAddress: " + custAddress 
				+ "\nPhone #: " + custPhone + "\nTicket ID: " + unpaidTicket.getID() + "\n";
		return unpaidTicketString;
	}
}
