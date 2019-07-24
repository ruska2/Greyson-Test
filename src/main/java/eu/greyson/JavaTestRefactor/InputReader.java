package eu.greyson.JavaTestRefactor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputReader {
	
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
	public String readInput() {
		System.out.print(MessageConstants.INPUT);
		String inputLine = null;

		try {
			inputLine = bufferedReader.readLine();

			//TODO add input validation for -h to show usage and basic validation
			if (ValidationConstants.QUIT.equals(inputLine.toLowerCase())) {
				return ValidationConstants.QUIT;
			}

			return inputLine;

		} catch (Exception e) {
			System.out.println(ValidationConstants.VALIDATION_INVALID_INPUT + inputLine);
			return null;
		}
	}
}
