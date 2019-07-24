package eu.greyson.JavaTestRefactor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaTestRefactorApplication implements CommandLineRunner {

	Boolean isReading = true;

	private Map<String, BigDecimal> paymentMap = new PaymentMap();

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
		System.out.println(MessageConstants.SEPARATOR + "\n" + MessageConstants.HEADER + "\n" + MessageConstants.SEPARATOR);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		// fileName argument check, if true try to read file

		while (isReading) {
			String input = readInput(bufferedReader);
			Payment payment = parsePayment(input);

			if (payment != null) {
				processPayment(payment);
			}


			System.out.println("\n\n=== Actual balances:"+paymentMap);
		}

		System.out.println(MessageConstants.SEPARATOR + "\n" + ValidationConstants.VALIDATION_EXIT);
	}

	public String readInput(BufferedReader bufferedReader) {
		System.out.print(MessageConstants.INPUT);
		String inputLine = null;

		try {
			inputLine = bufferedReader.readLine();

			//TODO add input validation for -h to show usage and basic validation
			if (ValidationConstants.QUIT.equals(inputLine.toLowerCase())) {
				isReading = false;
				return ValidationConstants.QUIT;
			}

			return inputLine;

		} catch (Exception e) {
			System.out.println(ValidationConstants.VALIDATION_INVALID_INPUT + inputLine);
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

}