package validate_sa_id; // Defines the package name for organizational purposes

import java.util.Scanner; // Imports the Scanner class for user input

public class IdValidatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creates a Scanner object to read input from the console
        System.out.println("South African ID Validator"); // Welcome message
        System.out.println("Enter a 13-digit ID number (or 'quit' to exit):"); // Prompt message for user input

        // Infinite loop to continuously prompt the user until they decide to quit
        while (true) {
            String input = scanner.nextLine().trim(); // Reads the input from user and trims whitespace

            // Check if the user wants to exit
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting validator."); // Exit message
                break; // Breaks the loop and ends the program
            }

            // Check if the user input is empty
            if (input.isEmpty()) {
                System.out.println("Error: Please enter an ID number."); // Error message for empty input
                continue; // Continues to the next iteration without validating
            }

            // Calls a method from another class to validate the ID number
            boolean isValid = ValidateSald.isIdNumberValid(input); // Replace 'ValidateSald' with correct class name if there's a typo
            // Displays whether the entered ID is valid or invalid
            System.out.println("ID " + input + " is " + (isValid ? "valid" : "invalid"));
            // Prompts the user for another input
            System.out.println("Enter another ID number (or 'quit' to exit):");
        }

        scanner.close(); // Closes the scanner to release system resources
    }
}
