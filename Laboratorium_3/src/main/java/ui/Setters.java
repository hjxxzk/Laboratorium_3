package ui;

import opinion.Type;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The `Setters` class provides static methods for setting various data types and handling user input in the user interface.
 */
public class Setters {
    /**
     * Scanner for user input
     */
    static Scanner scanner = new Scanner(System.in);

    /**
     * Sets an integer value based on user input.
     *
     * @param data The description of the data to be entered.
     * @return The entered integer value.
     * @throws IOException            If an I/O exception occurs.
     * @throws InterruptedException   If the execution is interrupted.
     */
    public static int setInt(String data) throws IOException, InterruptedException {

        System.out.println("Enter " + data + ": ");
        int input = getIntData();

        if (isAlright())
            return input;
        else
            setInt(data);
        return input;
    }

    /**
     * Sets a LocalDate object based on user input for date.
     *
     * @return The entered LocalDate object.
     * @throws IOException            If an I/O exception occurs.
     * @throws InterruptedException   If the execution is interrupted.
     */
    public static LocalDate setDate() throws IOException, InterruptedException {

        String input;
        LocalDate date = null;
        boolean test = false;

        while(!test) {
            try {
                System.out.println("Enter date (yyyy-mm-dd format): ");
                input = getStringData();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(input, formatter);
                test = true;

            } catch (DateTimeParseException e) {
                System.out.print("Incorrect input. Try again. ");
            }
        }
        if (isAlright())
            return date;
        else
            setDate();

        return date;
    }

    /**
     * Sets the Type (Positive/Negative) based on user input.
     *
     * @return The entered Type.
     */
    public static Type setType() {

        System.out.println("Negative or positive comment? Type p or n: ");
        String input = getStringData();

        if (input.equalsIgnoreCase("p")) {
            return Type.Positive;
        }   else if (input.equalsIgnoreCase("n")) {
            return Type.Negative;
        }

        System.out.println("Incorrect value. ");
        return setType();
    }

    /**
     * Sets the weight (1/2/3) based on user input.
     *
     * @return The entered weight.
     * @throws IOException          If an I/O exception occurs.
     * @throws InterruptedException If the execution is interrupted.
     */
    public static int setWeight() throws IOException, InterruptedException {

        System.out.println("Set weight (1 / 2 / 3). Enter number: ");
        int weight = getIntData();
        if (weight > 0 && weight < 4) {
            if (isAlright())
                return weight;
            else
                setWeight();
        } else {
            System.out.println("Incorrect value. ");
        }
        return setWeight();
    }

    /**
     * Sets a comment based on user input.
     *
     * @return The entered comment.
     * @throws IOException          If an I/O exception occurs.
     * @throws InterruptedException If the execution is interrupted.
     */
    public static String setComment() throws IOException, InterruptedException {

        System.out.println("Enter a comment (max. 350 characters)");
        String comment = getStringData();

        if (comment.length() > 350) {
            System.out.println("Input too long. Try again.");
            setComment();
        }

        if (isAlright())
            return comment;
        else
            setComment();

        return comment;
    }

    /**
     * Gets an integer value from user input.
     *
     * @return The entered integer value.
     */
    public static int getIntData() {
        int input = 0;
        boolean test = false;
        
        while (!test) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                test = true;

            } catch (InputMismatchException exception) {
                System.out.println("Incorrect input. ");
                System.out.print("Try again: ");
                scanner.nextLine();
            }
        }
        return input;
    }

    /**
     * Gets a string value from user input.
     *
     * @return The entered string value.
     */
    public static String getStringData() {
        return scanner.nextLine();
    }

    /**
     * Checks if the entered data is correct based on user input.
     *
     * @return True if the data is correct, false otherwise.
     * @throws IOException        If an I/O exception occurs.
     * @throws InterruptedException If the execution is interrupted.
     */
    public static boolean isAlright() throws IOException, InterruptedException {
        while (true) {
            System.out.println("Is the input correct? Type y or n: ");
            String answer = getStringData();
            if (answer.equalsIgnoreCase("y")) {
                cls();
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please type y or n.");
            }
        }
    }
    /**
     * Waits for user input to continue the program.
     */
    public static void waitForInput()  {
        System.out.println();
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Clears the console screen.
     *
     * @throws IOException        If an I/O exception occurs.
     * @throws InterruptedException If the execution is interrupted.
     */
    public static void cls() throws IOException, InterruptedException   {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
        Process process = processBuilder.inheritIO().start();
        process.waitFor();
    }


}
