import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class LineGraph extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Temperature in Celsius");

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time (seconds)");
		yAxis.setLabel("Degrees (Celsius)");

		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

		@SuppressWarnings("rawtypes")
		XYChart.Series series = new XYChart.Series();
		series.setName("Temperature Measurement");

		Random random = new Random();
		int n = 50 - 10 + 1;

		for(int i = 0; i < 300; i+= 5) {
			int j = random.nextInt() % n;
			int randomNumber = 10 + j;
			series.getData().add(new XYChart.Data(i, randomNumber));
		}
		Scene scene = new Scene(lineChart,800,600);
		lineChart.getData().add(series);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void sendTextIfTemperatureTooHigh() {
		final String username = "MadelineGilbert94@gmail.com";
		final String password = "chromomagnificence1";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("MadelineGilbert94@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("7082695303@vtext.com"));
			message.setSubject("");
			message.setText("Temperature out of range!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		ControllerFrame controller = new ControllerFrame();
		controller.setVisible(true);
		SerialComm main = new SerialComm();
		main.intialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
		//delay to get array readings
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		launch(args);

	}

}
