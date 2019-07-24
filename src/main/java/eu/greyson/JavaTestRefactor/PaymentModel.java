package eu.greyson.JavaTestRefactor;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class PaymentModel {
	
	private Map<String, BigDecimal> paymentMap = new PaymentMap();
	
	public void processPayment(Payment payment) {
		String curenncy = payment.getCurrency();
		BigDecimal amount = paymentMap.get(payment.getCurrency());

		if (amount != null) {
			paymentMap.put(curenncy, amount.add(payment.getAmount()));
			System.out.print(" (Updated)");
		} else {
			paymentMap.put(curenncy, payment.getAmount());
			System.out.print(" (Added new Currency:"+ curenncy);
		}
	}
}
