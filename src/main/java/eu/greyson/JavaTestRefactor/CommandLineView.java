package eu.greyson.JavaTestRefactor;

import lombok.Data;

@Data
public class CommandLineView {
	
	PaymentController paymentController;
	
	public void showMessage(String message){
		System.out.println(message);
	}
	
	public void showHeader(){
		System.out.println(MessageConstants.SEPARATOR + "\n" + MessageConstants.HEADER + "\n" + MessageConstants.SEPARATOR);
	}
	
	public void showEndMsg(){
		System.out.println(MessageConstants.SEPARATOR + "\n" + ValidationConstants.VALIDATION_EXIT);
	}
	
	public void showActualBalance(){
		System.out.println(MessageConstants.ACTUAL_BALANCES+paymentController.getPayments());
	}
	
	public void showWaitForInputmsg(){
		System.out.print(MessageConstants.INPUT);
	}
	
	public void showUpdatedPayment(){
		System.out.println(MessageConstants.UPDATED);
	}
	
	public void showAddedNewCurrency(String curenncy){
		System.out.println(MessageConstants.ADDED_NEW_CURRENCY+ curenncy + ")");
	}
	
	public void showInvalidInputMsg(){
		System.out.println(MessageConstants.INPUT_ERROR_MSG);
	}
	
	public void showInfo() {
		System.out.println(MessageConstants.INFO);
	}

}
