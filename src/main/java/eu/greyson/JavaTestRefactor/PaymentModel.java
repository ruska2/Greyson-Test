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
			updateCurenncy(curenncy, amount, payment);
		} else {
			addNewCurenncy(curenncy, amount, payment);
		}
	}
	
	private void addNewCurenncy(String curenncy, BigDecimal amount, Payment payment) {
		paymentMap.put(curenncy, payment.getAmount());
		paymentController.forwardAddedNewCurrency(curenncy);
	}
	
	private void updateCurenncy(String curenncy, BigDecimal amount, Payment payment) {
		paymentMap.put(curenncy, amount.add(payment.getAmount()));
		paymentController.forwardUpdated();
	}
}
