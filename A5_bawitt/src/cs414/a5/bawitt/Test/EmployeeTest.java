package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Employee;
import cs414.a5.bawitt.server.EmployeeImpl;

public class EmployeeTest {
	String un = "bwitt";
	String pn = "passw0rd";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testEmployee() throws RemoteException {
		Employee emp = new EmployeeImpl(un, pn);
		assertTrue(emp.getUsername().equals(un));
		assertTrue(emp.getIsActive()==true);
	}
	@Test
	public void testEmployeeNull() throws RemoteException {
		Employee emp = new EmployeeImpl();
		assertTrue(emp.getUsername()==null);
	}
	@Test
	public void testDeactivateUser() throws RemoteException {
		Employee emp = new EmployeeImpl(un,pn);
		assertTrue(emp.getIsActive()==true);
		emp.deactivateUser();
		assertTrue(!emp.getIsActive()==true);
	}
	@Test
	public void testIsApproved() throws RemoteException {
		String wrongPN = "wrongpn";
		Employee emp = new EmployeeImpl(un,pn);
		assertTrue(!emp.isApproved(wrongPN));
		assertTrue(emp.isApproved(pn));
		emp.deactivateUser();
		assertTrue(!emp.isApproved(pn));
	}
}
