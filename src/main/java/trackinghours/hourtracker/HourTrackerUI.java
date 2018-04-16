package trackinghours.hourtracker;

import java.util.Scanner;

/*
 * UI elements for the Hour tracker. Keeps an HourTracker obj on hand
 * for computational purposes
 */
public class HourTrackerUI {
	HourTracker hourTracker = new HourTracker();
	
	public void start() {
		System.out.println("Loading HourTracker.");
		hourTracker.loadHours();
		System.out.println("HourTracker Loaded.");
		System.out.println();
		System.out.println();
		System.out.println("\tWelcome!");
		mainMenuLoop();
		hourTracker.saveHours();
	}
	
	//Prints the main menu.
	public void printMainMenu() {
		System.out.println("Please Select an option below:");
		System.out.println();
		System.out.println();
		System.out.println("1. Add coding hours.");
		System.out.println("2. Add gaming hours.");
		System.out.println("3. Quit.");
		System.out.println();
	}
	
	//Prints analysis of hours used.
	public void printHourAnalysis() {
		int difference = hourTracker.getDifference();
		int hoursEarned = hourTracker.getHoursEarned();
		int codingHours = hourTracker.getCodingHours();
		int gamingHours = hourTracker.getGamingHours();
		printStats(codingHours, gamingHours);
		if (difference > 0) {
			System.out.println();
			System.out.println("Well done. You've earned " + hoursEarned + " hours of gaming.");
		}
		else if (difference == 0) {
			System.out.println("Well, you don't currently have any gaming hours available.");
		}
		else {
			System.out.println("You NAUGHTY BOY! You currently owe: " + hoursEarned + " hours!");
		}
		
	}
	
	//Prints stats
	public void printStats(int codingHours, int gamingHours) {
		System.out.println();
		System.out.println("You have coded for: " + codingHours + " hours so far.");
		System.out.println("You have gamed for: " + gamingHours + " hours so far");
		System.out.println();
		
	}
	
	public void mainMenuLoop() {
		boolean notDone = true;
		Scanner keyboard = new Scanner(System.in);
		int numHours = 0;
		while (notDone) {
			printHourAnalysis();
			printMainMenu();
			char input = keyboard.next().charAt(0);
			switch (input){
			case '1':
				System.out.println();
				System.out.println("Enter the number of additional coding hours: ");
				numHours = keyboard.nextInt();
				hourTracker.addCodingHours(numHours);
				break;
			case '2':
				System.out.println();
				System.out.println("Enter the number of additional gaming hours: ");
				numHours = keyboard.nextInt();
				hourTracker.addGamingHours(numHours);
				break;
			case '3':
				notDone = false;
				break;
			default:
				System.out.println("Please select a valid input from the list.");
			}
			
		}
		System.out.println();
		System.out.println("See ya next time!");
		keyboard.close();
	}

}
