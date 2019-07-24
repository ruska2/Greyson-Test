package eu.greyson.JavaTestRefactor;

import java.io.IOException;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaTestRefactorApplication implements CommandLineRunner {


	PaymentModel paymentModel;
	PaymentController paymentController;
	CommandLineView commandLineView;
	
	public JavaTestRefactorApplication() {
		initializeComponents();
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
	
	private void initializeComponents(){
		paymentModel = new PaymentModel();
		paymentController = new PaymentController();
		commandLineView = new CommandLineView();
		paymentModel.setPaymentController(paymentController);
		paymentController.setPaymentModel(paymentModel);
		commandLineView.setPaymentController(paymentController);
		paymentController.setCommandLineView(commandLineView);
	}
	
	private void trackerService() {
		commandLineView.showHeader();
		InputReader reader = new InputReader();
		
		// fileName argument check, if true try to read file
		while (true) {
			String input = "";
			commandLineView.showWaitForInputmsg();
			try {
				input = reader.readInput();
			} catch (IOException e) {
				commandLineView.showMessage(ValidationConstants.VALIDATION_INVALID_INPUT + input);
			}

			if (input.equals(ValidationConstants.QUIT)){
				commandLineView.showActualBalance();
				break;
			}
			paymentController.processPayment(input);
		}
		commandLineView.showEndMsg();
	}
}