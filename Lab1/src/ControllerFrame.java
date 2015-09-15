import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ControllerFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static JButton unitButton;
	private static JButton onOffButton;
	private static JLabel temperatureLabel;
	private double currentTemperature = (float) 24.2;
	private static JTextField phoneNumber;
	private static JTextField tempSensitivityMax;
	private static JTextField tempSensitivityMin;
	private static String phoneNumberStored = "7082695303";
	private static String maxTempLevel = "60";
	private static String minTempLevel = "0";
	private static JLabel temp;
	private static JLabel phone;
	private static JLabel sensitivityMax;
	private static JLabel sensitivityMin;

	public ControllerFrame()
	{
		JPanel contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new GridLayout(5, 2));
	    setContentPane(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(300, 350);

	    sensitivityMin = new JLabel("Min Temperature (C):");
	    sensitivityMax = new JLabel("Max Temperature (C):");
	    phone = new JLabel("Phone Number:");
	    temp = new JLabel("Temperature:");
		unitButton = new JButton("Celsius");
		onOffButton = new JButton("Off");
		temperatureLabel = new JLabel("24.2");
		phoneNumber = new JTextField("7082695303");
		tempSensitivityMax = new JTextField("60");
		tempSensitivityMin = new JTextField("0");
		
		onOffButton.addActionListener(this);
		unitButton.addActionListener(this);
		phoneNumber.addActionListener(this);
		tempSensitivityMax.addActionListener(this);
		tempSensitivityMin.addActionListener(this);
		
		this.add(temp);
		this.add(temperatureLabel);
		this.add(phone);
		this.add(phoneNumber);
		this.add(sensitivityMax);
		this.add(tempSensitivityMax);
		this.add(sensitivityMin);
		this.add(tempSensitivityMin);
		this.add(onOffButton);
		this.add(unitButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == unitButton) {
			if (unitButton.getText() == "Celsius") {
				unitButton.setText("Fahrenheit");
				double newFTemp = (float)(currentTemperature * 1.8) + 32;
				currentTemperature = newFTemp;
				String tempFString = String.format("		%.2f°F",newFTemp);
				temperatureLabel.setText(tempFString);
			} else if (unitButton.getText() == "Fahrenheit") {
				unitButton.setText("Celsius");
				double newCTemp = (float)(currentTemperature-32.0) * 0.56;
				currentTemperature = newCTemp;
				String tempCString = String.format("		%.2f°C",newCTemp);
				temperatureLabel.setText(tempCString);
			}
		} else if (e.getSource() == onOffButton) {
			System.out.print("On/Off Pressed");
			if(onOffButton.getText() == "Off") {
				onOffButton.setText("On");
			} else if(onOffButton.getText() == "On") {
				onOffButton.setText("Off");
			}
		} else if (e.getSource() == phoneNumber) {
			phoneNumberStored = phoneNumber.getText();
			System.out.print(phoneNumberStored);
		} else if (e.getSource() == tempSensitivityMax) {
			maxTempLevel = tempSensitivityMax.getText();
			System.out.print(maxTempLevel);
		} else if (e.getSource() == tempSensitivityMin) {
			minTempLevel = tempSensitivityMin.getText();
			System.out.print(minTempLevel);
		}
	}

}
