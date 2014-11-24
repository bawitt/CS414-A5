package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Garage;
import cs414.a5.bawitt.server.GarageImpl;
import cs414.a5.bawitt.server.SignStatusImpl;
import cs414.a5.bawitt.server.TicketImpl;

public class GarageTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateEmployee() throws RemoteException {
		Garage g = new GarageImpl();
		assertTrue(g.getEmployeeList().size()==1);
		g.createEmployee("baw", "12345");
		assertTrue(g.getEmployeeList().size()==2);
	}
	@Test
	public void testAuthorizeUser() throws RemoteException{
		Garage g = new GarageImpl();
		g.createEmployee("baw", "12345");
		assertTrue(!g.authorizeUser("baw","1234"));
		assertTrue(g.authorizeUser("baw","12345"));
	}
	@Test
	public void testIssueTicket() throws RemoteException {
		Garage g = new GarageImpl();
		assertTrue(g.getActiveTicketList().size()==0);
		g.issueTicket();
		assertTrue(g.getActiveTicketList().size()==1);
	}
	@Test
	public void testUpdateGarageSpaces() throws RemoteException {
		Garage g = new GarageImpl();
		g.issueTicket();
		assertTrue(g.getSignStatus()==SignStatusImpl.vacancy);
		g.updateGarageSpaces(1);
		assertTrue(g.getSignStatus()==SignStatusImpl.full);
		g.updateGarageSpaces(2);
		assertTrue(g.getSignStatus()==SignStatusImpl.vacancy);
	}
	@Test
	public void testExitGarageWithTicket() throws RemoteException {
		Garage g = new GarageImpl();
		assertTrue(g.getActiveTicketList().size()==0);
		g.issueTicket();
		TicketImpl t = g.getTicketFromList(1);
		assertTrue(g.getActiveTicketList().size()==1);
		assertTrue(g.getPaidTicketList().size()==0);
		g.exitGarage(t.getID());
		assertTrue(g.getActiveTicketList().size()==0);
		assertTrue(g.getPaidTicketList().size()==1);
	}
	@Test
	public void testExitGarageWithout() throws RemoteException {
		Garage g = new GarageImpl();
		assertTrue(g.getGarageUsedSpaces()==0);
		g.issueTicket();
		assertTrue(g.getGarageUsedSpaces()==1);
		g.exitGarage();
		assertTrue(g.getGarageUsedSpaces()==0);
	}
}
