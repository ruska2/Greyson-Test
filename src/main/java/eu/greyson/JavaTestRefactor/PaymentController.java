package eu.greyson.JavaTestRefactor;

import lombok.Data;

@Data
public class PaymentController {
	
	PaymentModel paymentModel;
	Validate validate = new Validate();
	
	public Payment parsePayment(String payment) {
		if (validate.isValidInputFormat(payment)) {
			String[] paymentSplit = payment.split(" ");
			return new Payment(paymentSplit[0].toUpperCase(), paymentSplit[1]);
		}
		return null;
	}

}
