package a5.application;

public interface EmployeeImpl {

	boolean isApproved(String pn);

	void deactivateUser();

	String getUsername();

	boolean getIsActive();

}