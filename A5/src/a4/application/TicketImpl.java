package a4.application;

import org.joda.time.DateTime;

public interface TicketImpl {

	//hours from entry until current time
	int getDurationHours();

	int getID();

	DateTime getEnterDate();

	void setPaymentDate(DateTime dt);

	double getTicketStandardRate();

	double getAmountDue();

}