package eu.greyson.JavaTestRefactor;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class Validate {
	/***
	 * Input validation for the file or keyboard input
	 *
	 * @param input String representations of payment or other text (like the "quit" abort keyword)
	 * @return <b>true</b> only if the payment string representation has a 3 letter currency and a number amount
	 * <p><b>false</b> if invalid payment or aborting application using the "quit" string
	 */
	public boolean isValidInputFormat(String input) {

		if (ValidationConstants.QUIT.equals(input)) {
			System.out.println(ValidationConstants.VALIDATION_EXIT);
			return false;
		}

		if (StringUtils.isBlank(input)) {
			return false;
		}

		String[] paymentSplit = input.trim().split(" ");

		if (paymentSplit.length != 2) {
			System.out.println(ValidationConstants.VALIDATION_INVALID_INPUT + input);
			return false;
		}

		if (paymentSplit[0].length() != 3) {
			System.out.println(ValidationConstants.VALIDATION_3_LETTERS);
			return false;
		}

		try {
			new BigDecimal(paymentSplit[1]);
		} catch (NumberFormatException nfe) {
			System.out.println(ValidationConstants.VALIDATION_AMOUNT_NUMBER);
			return false;
		}

		return true;
	}
}