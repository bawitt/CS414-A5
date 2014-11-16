package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a5.application.Employee;
import a5.application.EmployeeImpl;

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
	public void testEmployee() {
		EmployeeImpl emp = new Employee(un, pn);
		assertTrue(emp.getUsername().equals(un));
		assertTrue(emp.getIsActive()==true);
	}
	@Test
	public void testEmployeeNull() {
		EmployeeImpl emp = new Employee();
		assertTrue(emp.getUsername()==null);
	}
	@Test
	public void testDeactivateUser() {
		EmployeeImpl emp = new Employee(un,pn);
		assertTrue(emp.getIsActive()==true);
		emp.deactivateUser();
		assertTrue(!emp.getIsActive()==true);
	}
	@Test
	public void testIsApproved() {
		String wrongPN = "wrongpn";
		EmployeeImpl emp = new Employee(un,pn);
		assertTrue(!emp.isApproved(wrongPN));
		assertTrue(emp.isApproved(pn));
		emp.deactivateUser();
		assertTrue(!emp.isApproved(pn));
	}
}
