package utilites;

import java.util.Scanner;

public class HandleIntInput {

    private HandleIntInput() {};

    public static int handleUserInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid choice");
            scanner.nextLine();
        }

        int inputChoice = scanner.nextInt();
        scanner.nextLine();

        return inputChoice;
    }
}
