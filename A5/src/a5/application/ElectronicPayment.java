package a5.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ElectronicPayment extends Payment implements ElectronicPaymentImpl{
	private String accountNum;
	private String expDate;
	//private double amountDue;
	
	public ElectronicPayment(double ad, String act, String ed){
		super(ad, PaymentType.electronic);
		accountNum = act;
		//amountDue = ad;	
		expDate = ed;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.ElectronicPaymentImpl#isAccountValid()
	 */
	@Override
	public boolean isAccountValid(){
		if(isDateValid()&&isActNumValid()){
			return true;
		}
		else return false;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.ElectronicPaymentImpl#isDateValid()
	 */
	@Override
	public boolean isDateValid(){  //check date format
		String ed = expDate;
		SimpleDateFormat dtfmt = new SimpleDateFormat("MM/yyyy");
		Date date = null;
		try{
			date = dtfmt.parse(ed); 
		}
		catch (ParseException e)
        {
            System.out.println("Date format is invalid");
            return false;
        }
		return true;
	}
	/* (non-Javadoc)
	 * @see a4.application.ElectronicPaymentImpl#isActNumValid()
	 */
	@Override
	public boolean isActNumValid(){
		String actNum= accountNum;
	    for(char c : actNum.toCharArray()) //check that actNum is all digits
	    {
	        if(!Character.isDigit(c)){
	        	System.out.println("\nAccount number format is invalid.\n");
	        	return false;
	        }
	    }
		if(actNum.length()!=16){  //check that actNum is correct length
			System.out.println("Account number length is invalid.");
			return false;
		}
		return true;
	}
}
