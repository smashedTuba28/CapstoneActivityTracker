package edu.ycp.cs320.CapstoneActivityTracker.RFID;

import java.util.Date;
import java.util.Scanner;

public class RFIDMain {
	private static RFIDController controller;

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		controller = new RFIDController();
	
		while(true) {
		
			System.out.println("Incoming String from Scanner: ");
			String scan = keyboard.nextLine();
		
			if(scan.toLowerCase().equals("exit")) {
				System.out.println("RFID TURNED OFF");
				break;
			}
			//example of valid input
			//schoolID|room#|Timestamp
			//900000000|128|20190422090000
			controller.handleEvent(scan);
		
		}
	}
}
