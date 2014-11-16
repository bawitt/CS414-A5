package a4.application;

import java.util.Date;

public interface PaymentImpl {

	double getAmountDue();

	Date getPaymentDate();

	PaymentType getPaymentType();

	double getOriginalAmountDue();

}