package eu.greyson.JavaTestRefactor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaTestRefactorApplication implements CommandLineRunner {


	PaymentModel paymentModel;
	PaymentController paymentController;
	
	public JavaTestRefactorApplication() {
		paymentModel = new PaymentModel();
		paymentController = new PaymentController();
		paymentModel.setPaymentController(paymentController);
		paymentController.setPaymentModel(paymentModel);
	}

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

		while (true) {
			String input = readInput(bufferedReader);
			Payment payment = paymentController.parsePayment(input);

			if (payment != null) {
				paymentModel.processPayment(payment);
			}
			
			


			System.out.println("\n\n=== Actual balances:"+paymentModel.getPaymentMap());
			
			if (input.equals(ValidationConstants.QUIT)){
				break;
			}
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
				return ValidationConstants.QUIT;
			}

			return inputLine;

		} catch (Exception e) {
			System.out.println(ValidationConstants.VALIDATION_INVALID_INPUT + inputLine);
			return null;
		}
	}


}