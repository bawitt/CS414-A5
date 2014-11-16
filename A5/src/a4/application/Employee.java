package a4.application;

public class Employee implements EmployeeImpl {
	String username;
	String passNum;
	boolean isAdmin;
	boolean isActive;
	
	public Employee(String un, String pn){
		username = un;
		passNum = pn;
		isActive = true;
	}
	
	public Employee() {
		username = null;
	}
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#isApproved(java.lang.String)
	 */
	@Override
	public boolean isApproved(String pn){
		if(isActive && (passNum.equals(pn))){
			return true;
		}
		else return false;
	}
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#deactivateUser()
	 */
	@Override
	public void deactivateUser(){
		isActive = false;
	}
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#getUsername()
	 */
	@Override
	public String getUsername(){
		return username;
	}
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#getIsActive()
	 */
	@Override
	public boolean getIsActive(){
		return isActive;
	}
	
}
