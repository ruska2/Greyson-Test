package eu.greyson.JavaTestRefactor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JavaTestRefactorApplication implements CommandLineRunner {

	public static final String QUIT = "quit";
	public static final String INPUT = "Input > ";
	public static final String SEPARATOR = "=================================================";
	public static final String HEADER = "Payment Tracker application";
	public static final String VALIDATION_EXIT = "Exiting application.";
	public static final String VALIDATION_INVALID_INPUT = "Invalid input: ";
	public static final String VALIDATION_3_LETTERS = "Invalid input. Currency must be 3 letters !";
	public static final String VALIDATION_AMOUNT_NUMBER = "Invalid input. Amount must consit of only numbers!";

	Boolean isReading = true;

	private Map<String, BigDecimal> paymentMap = new HashMap<>();

	Validate validate = new Validate();


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(JavaTestRefactorApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setLogStartupInfo(false);
		app.run(args);
	}

	@Override
	public void run(String... args) {
		trackerService();
	}

	private void trackerService() {
		System.out.println(SEPARATOR + "\n" + HEADER + "\n" + SEPARATOR);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		// fileName argument check, if true try to read file

		while (isReading) {
			String input = readInput(bufferedReader);
			Payment payment = parsePayment(input);

			if (payment != null) {
				processPayment(payment);
			}


			System.out.println("\n\n=== Actual balances:"+getNetCurrencies());
		}

		System.out.println(SEPARATOR + "\n" + VALIDATION_EXIT);
	}

	public String readInput(BufferedReader bufferedReader) {
		System.out.print(INPUT);
		String inputLine = null;

		try {
			inputLine = bufferedReader.readLine();

			//TODO add input validation for -h to show usage and basic validation
			if (QUIT.equals(inputLine.toLowerCase())) {
				isReading = false;
				return QUIT;
			}

			return inputLine;

		} catch (Exception e) {
			System.out.println(VALIDATION_INVALID_INPUT + inputLine);
			return null;
		}
	}

	public Payment parsePayment(String payment) {
		if (validate.isValidInputFormat(payment)) {
			String[] paymentSplit = payment.split(" ");
			return new Payment(paymentSplit[0].toUpperCase(), paymentSplit[1]);
		}
		return null;
	}

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

	public String getNetCurrencies() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		paymentMap.forEach((k, v) -> {
			if (v != BigDecimal.ZERO) {
				builder.append("> "+k + " " + v + "\n");
			}
		});

		if (StringUtils.isBlank(builder.toString())) {
			return "";
		}

		return builder.toString();
	}
}