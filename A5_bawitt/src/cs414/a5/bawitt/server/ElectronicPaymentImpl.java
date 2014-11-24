package cs414.a5.bawitt.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cs414.a5.bawitt.common.ElectronicPayment;

public class ElectronicPaymentImpl extends PaymentImpl implements ElectronicPayment{

	private static final long serialVersionUID = 1L;
	private String accountNum;
	private String expDate;
	
	public ElectronicPaymentImpl(double ad, String act, String ed, int t) throws java.rmi.RemoteException{
		super(ad, PaymentTypeImpl.electronic, t);
		accountNum = act;	
		expDate = ed;
	}
	
	@Override
	public boolean isAccountValid() throws java.rmi.RemoteException{
		if(isDateValid() && isActNumValid() && isExpDateInFuture()){
			return true;
		}
		else return false;
	}
	
	@Override
	public boolean isDateValid() throws java.rmi.RemoteException{  //check date format
		String ed = expDate;
		SimpleDateFormat dtfmt = new SimpleDateFormat("MM/yyyy");
		try{
			dtfmt.parse(ed); 
		}
		catch (ParseException e)
        {
            return false;
        }
		return true;
	}

	@Override
	public boolean isActNumValid() throws java.rmi.RemoteException{
		String actNum= accountNum;
	    for(char c : actNum.toCharArray()) //check that actNum is all digits
	    {
	        if(!Character.isDigit(c)){
	        	return false;
	        }
	    }
		if(actNum.length()!=16){  //check that actNum is correct length
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isExpDateInFuture(){
		try{
			int userMonth = Integer.parseInt(expDate.substring(0, 2));
			int userYear = Integer.parseInt(expDate.substring(3, 7));
			int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			if(userMonth>12) return false;
			if(userYear<currentYear) return false;
			if(userYear==currentYear && userMonth<=currentMonth) return false;
			return true;
		}catch (NumberFormatException e1){
			return false;
		}
	}
}
