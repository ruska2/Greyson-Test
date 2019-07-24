package eu.greyson.JavaTestRefactor;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class PaymentModel {
	
	private Map<String, BigDecimal> paymentMap = new PaymentMap();
	
	private PaymentController paymentController;
	
	public void processPayment(Payment payment) {
		String curenncy = payment.getCurrency();
		BigDecimal amount = paymentMap.get(payment.getCurrency());

		if (amount != null) {
			paymentMap.put(curenncy, amount.add(payment.getAmount()));
			paymentController.forwardUpdated();
		} else {
			paymentMap.put(curenncy, payment.getAmount());
			paymentController.forwardAddedNewCurrency(curenncy);
		}
	}
}
