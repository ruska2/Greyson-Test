package eu.greyson.JavaTestRefactor;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class Validate {
	/***
	 * Input validation for the file or keyboard input
	 *
	 * @param input String representations of payment or other text (like the "quit" abort keyword)
	 * @return <b>true</b> only if the payment string representation has a 3 letter currency and a number amount
	 * <p><b>false</b> if invalid payment or aborting application using the "quit" string
	 */
	
	private String errorMsg = "";
	
	public boolean isValidInputFormat(String input) {
		
		errorMsg = "";
		
		if (isBlank(input)) return false;

		String[] paymentSplit = input.trim().split(" ");

		if (!isValidSplit(paymentSplit.length, input)) return false;

		if (!isValidCurenncy(paymentSplit[0])) return false;

		if (!isValidNumber(paymentSplit[1])) return false;
		
		return true;
	}
	
	private boolean isBlank(String input) {
		if (StringUtils.isBlank(input)) {
			errorMsg = ValidationConstants.VALIDATION_BLANK_INPUT;
			return true;
		}
		return false;
	}
	
	private boolean isValidSplit(int paymentSplit, String input) {
		if (paymentSplit != 2) {
			errorMsg = ValidationConstants.VALIDATION_INVALID_INPUT + input;
			return false;
		}
		return true;
	}
	
	private boolean isValidCurenncy(String input) {
		if (input.length() != 3) {
			errorMsg = ValidationConstants.VALIDATION_3_LETTERS;
			return false;
		}
		return true;
	}
	
	private boolean isValidNumber(String input) {
		try {
			new BigDecimal(input);
			return true;
		} catch (NumberFormatException nfe) {
			errorMsg = ValidationConstants.VALIDATION_AMOUNT_NUMBER;
			return false;
		}
	}
}