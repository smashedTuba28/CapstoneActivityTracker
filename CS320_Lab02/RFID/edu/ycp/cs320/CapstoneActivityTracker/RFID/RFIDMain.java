package edu.ycp.cs320.CapstoneActivityTracker.RFID;

import java.util.Scanner;

public class RFIDMain {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter First Name:");
		String f = keyboard.nextLine();
		System.out.println("Enter Last Name:");
		String l = keyboard.nextLine();
		
		
		System.out.println("Hello, " + f + " " + l);
	}

}
