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
		//TODO add input validation for -h to show usage and basic validation
		if (ValidationConstants.QUIT.equals(inputLine.toLowerCase())) {
			return ValidationConstants.QUIT;
		}

		return inputLine;
	}
}
