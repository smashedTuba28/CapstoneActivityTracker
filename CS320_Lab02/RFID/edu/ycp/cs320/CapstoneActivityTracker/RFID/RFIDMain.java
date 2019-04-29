package edu.ycp.cs320.CapstoneActivityTracker.RFID;

import java.util.Date;
import java.util.Scanner;

public class RFIDMain {
	private static RFIDController controller;

	public static void main(String[] args) {
	
		while(true) {
			Scanner keyboard = new Scanner(System.in);

			System.out.println("Enter SchoolID:");
			String schoolID = keyboard.nextLine();
			
			System.out.println("Enter Room Name:");
			String roomName = keyboard.nextLine();
		
			
			Date time = new Date();
			
			controller = new RFIDController();
			
			controller.scan(schoolID, roomName, time);
			
		}
	}
}
