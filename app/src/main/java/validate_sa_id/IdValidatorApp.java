package validate_sa_id;

import java.util.Scanner;

public class IdValidatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("South African ID Validator");
        System.out.println("Enter a 13-digit ID number (or 'quit' to exit):");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting validator.");
                break;
            }

            if (input.isEmpty()) {
                System.out.println("Error: Please enter an ID number.");
                continue;
            }

            boolean isValid = ValidateSald.isIdNumberValid(input);
            System.out.println("ID " + input + " is " + (isValid ? "valid" : "invalid"));
            System.out.println("Enter another ID number (or 'quit' to exit):");
        }

        scanner.close();
    }
}