package com.winter.ejemplos;

import java.util.Random;
import java.util.Scanner;

public class Average {

	public static void clock() {
		// Simulate the clock.
		for (int hours = 1; hours <= 22; hours++) {
			for (int minutes = 0; minutes <= 59; minutes++) {
				for (int seconds = 0; seconds <= 59; seconds++) {
					System.out.printf("%02d:%02d:%02d\n", hours, minutes, seconds);
				}
			}
		}
	}

	public static void averageDoWhile() {
		int score1, score2, score3;
		double average;
		char repeat;
		String input;
		System.out.println("this program calculate the average of three test scores.");
		// Create a Scanner object for keyboard input.
		Scanner keyboard = new Scanner(System.in);
		// Get as many sets of test scores as the user wants.
		do {
			// Get the first test score in this set.
			System.out.print("Enter sc2ore #1: ");
			score1 = keyboard.nextInt();
			// Get the second test score in this set.
			System.out.print("Enter score #2: ");
			score2 = keyboard.nextInt();
			// Get the third test score in this set.
			System.out.print("Enter score #3: ");
			score3 = keyboard.nextInt();
			// Consume the remaining newline.
			keyboard.nextLine();
			// Calculate and print the average test score.
			average = (score1 + score2 + score3) / 3.0;
			System.out.println("The average is " + average);
			System.out.println(); // Prints a blank line
			// Does the user want to average another set?
			System.out.println("would you like to average another set of test scores?");
			System.out.print("Enter Y for yes or N for no: ");
			input = keyboard.nextLine(); // Read a line.
			repeat = input.charAt(0); // Get the first char.
		} while (repeat == 'Y' || repeat == 'y');
		keyboard.close();
	}

	public static void averageFor() {
		int numStudents, numTests;
		double score, average, total;
		Scanner keyboard = new Scanner(System.in);
		// Get the number of students.
		System.out.print("How many students do you have? ");
		numStudents = keyboard.nextInt();
		// Get the number of test scores per student.
		System.out.print("How many test scores per student? ");
		numTests = keyboard.nextInt();
		// Process all the students.
		for (int student = 1; student <= numStudents; student++) {
			total = 0.0; // Set the accumulator to zero.
			// Get the test scores for a student.
			System.out.println("Student number " + student);
			for (int test = 1; test <= numTests; test++) {
				System.out.print("Enter score " + test + ": ");
				score = keyboard.nextDouble();
				total += score; // Add score to total.
			}
			// Calculate and display the average.
			average = total / numTests;
			System.out.printf("The average for student %d is %.1f.", student, average);
			System.out.println();
		}
		keyboard.close();
	}

	public static void mathTutor() {
		int number1, number2, sum, userAnswer;
		Scanner keyboard = new Scanner(System.in);
		// Create a Random class object.
		Random randomNumbers = new Random();
		// Get two random numbers.
		number1 = randomNumbers.nextInt(100);
		number2 = randomNumbers.nextInt(100);
		// Display an addition problem.
		System.out.println("what is the answer to the following problem?");
		System.out.print(number1 + " + " + number2 + " = ? ");
		// Calculate the answer.
		sum = number1 + number2;
		// Get the user's answer.
		userAnswer = keyboard.nextInt();
		// Display the user's results.
		if (userAnswer == sum)
			System.out.println("Correct!");
		else
			System.out.println("Sorry, wrong answer! The correct answer is " + sum);
		keyboard.close();
	}

	public static void main(String[] args) {
		// which is the sum
		//mathTutor();
		//
		clock();
	}
}
