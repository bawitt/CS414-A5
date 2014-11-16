package a5.application;

import java.util.Set;

public interface GarageImpl {

	void updateSignStatus();

	void updateGarageSpaces(int numSpaces);

	void updateGarageStandardRate(double r);

	void updateGarageFlatRate(double r);

	void issueTicket();

	Ticket getTicketFromList(int id);

	EmployeeImpl getEmployeeFromList(String un);

	boolean authorizeUser(String un, String pn);

	void exitGarage(Ticket ticket);

	void exitGarage();

	void createEmployee(String un, String pn);

	RateImpl getGarageRate();

	SignStatus getSignStatus();

	double getGarageFlatRate();

	Set<Employee> getEmployeeList();

	Set<Ticket> getActiveTicketList();

	void showUnpaidTickets();

	Set<Ticket> getPaidTicketList();

	int getGarageUsedSpaces();

	void addReceiptToCollection(Receipt r);

	void addUnpaidTicketToList(UnpaidTicket ut);

	void showsSpaceStatus();

}