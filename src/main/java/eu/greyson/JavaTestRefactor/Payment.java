package eu.greyson.JavaTestRefactor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Payment {

	private String currency;

	private BigDecimal amount;

	public Payment(String currency, String amount) {
		this.currency = currency;
		this.amount = new BigDecimal(amount);
	}

	@Override
	public String toString() {
		return currency + " " + amount;
	}
}
