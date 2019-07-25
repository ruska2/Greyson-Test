package eu.greyson.JavaTestRefactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.Data;

@Data
public class InputReader {
	
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public String readInput() throws IOException {
		String inputLine = null;
	
		inputLine = bufferedReader.readLine();
		
		if (ValidationConstants.QUIT.equals(inputLine.toLowerCase())) {
			return ValidationConstants.QUIT;
		}
		
		if (ValidationConstants.INFO_SIGN.equals(inputLine.toLowerCase())) {
			return ValidationConstants.INFO_SIGN;
		}

		return inputLine;
	}
}
