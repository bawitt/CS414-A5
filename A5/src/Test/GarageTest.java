package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a5.application.Garage;
import a5.application.GarageImpl;
import a5.application.SignStatus;
import a5.application.Ticket;

public class GarageTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateEmployee() {
		GarageImpl g = new Garage();
		assertTrue(g.getEmployeeList().size()==1);
		g.createEmployee("baw", "12345");
		assertTrue(g.getEmployeeList().size()==2);
	}
	@Test
	public void testAuthorizeUser(){
		GarageImpl g = new Garage();
		g.createEmployee("baw", "12345");
		assertTrue(!g.authorizeUser("baw","1234"));
		assertTrue(g.authorizeUser("baw","12345"));
	}
	@Test
	public void testIssueTicket(){
		GarageImpl g = new Garage();
		assertTrue(g.getActiveTicketList().size()==0);
		g.issueTicket();
		assertTrue(g.getActiveTicketList().size()==1);
	}
	@Test
	public void testUpdateGarageSpaces(){
		GarageImpl g = new Garage();
		g.issueTicket();
		assertTrue(g.getSignStatus()==SignStatus.vacancy);
		g.updateGarageSpaces(1);
		assertTrue(g.getSignStatus()==SignStatus.full);
		g.updateGarageSpaces(2);
		assertTrue(g.getSignStatus()==SignStatus.vacancy);
	}
	@Test
	public void testExitGarageWithTicket(){
		GarageImpl g = new Garage();
		assertTrue(g.getActiveTicketList().size()==0);
		g.issueTicket();
		Ticket t = g.getTicketFromList(1);
		assertTrue(g.getActiveTicketList().size()==1);
		assertTrue(g.getPaidTicketList().size()==0);
		g.exitGarage(t);
		assertTrue(g.getActiveTicketList().size()==0);
		assertTrue(g.getPaidTicketList().size()==1);
	}
	@Test
	public void testExitGarageWithout(){
		GarageImpl g = new Garage();
		assertTrue(g.getGarageUsedSpaces()==0);
		g.issueTicket();
		assertTrue(g.getGarageUsedSpaces()==1);
		g.exitGarage();
		assertTrue(g.getGarageUsedSpaces()==0);
	}
}
