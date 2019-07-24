package eu.greyson.JavaTestRefactor;

import lombok.Data;

@Data
public class PaymentController {
	
	PaymentModel paymentModel;
	CommandLineView commandLineView;
	
	Validate validate = new Validate();
	
	public Payment parsePayment(String payment) {
		if (validate.isValidInputFormat(payment)) {
			String[] paymentSplit = payment.split(" ");
			return new Payment(paymentSplit[0].toUpperCase(), paymentSplit[1]);
		}
		return null;
	}
	
	public void forwardMessage(String message){
		commandLineView.showMessage(message);
	}
	
	public void processPayment(String input){
		Payment payment = parsePayment(input);

		if (payment != null) {
			paymentModel.processPayment(payment);
		}
		
		commandLineView.showActualBalance();
	}
	
	public PaymentMap getPayments(){
		return (PaymentMap) paymentModel.getPaymentMap();
	}
	
	public void forwardUpdated(){
		commandLineView.showUpdatedPayment();
	}
	
	public void forwardAddedNewCurrency(String curenncy){
		commandLineView.showAddedNewCurrency(curenncy);
	}

}
