package com.winter.ejemplos;

import javax.swing.JOptionPane;

public class NamesDialog {

	public static void main(String[] args) {
		String firstName;
		double monthlyPay = 5000.0;
		double annualPay = monthlyPay * 12;
		String output = String.format("Your annual pay is $%,.2f", annualPay);

		// Get the user's names.
		firstName = JOptionPane.showInputDialog("what is your first name?");

		// Display a greeting
		JOptionPane.showMessageDialog(null, "hi, " + firstName + " " + output);
		
		// The value 0 traditionally indicates that the program ended successfully
		System.exit(0);
	}

}
