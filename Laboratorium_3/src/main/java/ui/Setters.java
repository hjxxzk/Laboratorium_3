package ui;

import opinion.Type;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Setters {

    static Scanner scanner = new Scanner(System.in);

    public static int setInt(String data) throws IOException, InterruptedException {

        System.out.println("Enter " + data + ": ");
        int input = getIntData();

        if (isAlright())
            return input;
        else
            setInt(data);
        return input;
    }


    public static LocalDate setDate() throws IOException, InterruptedException {

        System.out.println("Enter date (yyyy-mm-dd format): ");
        String input = getStringData();

        LocalDate date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(input, formatter);

        } catch (DateTimeParseException e) {
            System.out.print("Incorrect input. Try again. ");
            setDate();
        }

        if (isAlright())
            return date;
        else
            setDate();

        return date;
    }

    public static Type setType() {

        System.out.println("Negative or positive comment? Type p or n: ");
        String input = getStringData();

        if (input.equalsIgnoreCase("p")) {
            return Type.Positive;
        }   else if (input.equalsIgnoreCase("n")) {
            return Type.Negative;
        }
        return setType();
    }

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


    public static int getIntData() {
        int input = 0;
        try {
            input = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException exception)  {
            System.out.println("Incorrect input. ");
            System.out.print("Try again: ");
            scanner.nextLine();
            getIntData();
        }
        return input;
    }

    public static String getStringData() {
        return scanner.nextLine();
    }

    public static boolean isAlright() throws IOException, InterruptedException {
        System.out.println("Is the input correct? Type y or n: ");
        String answer = getStringData();

        if (answer.equalsIgnoreCase("y")) {
            cls();
            return true;
        }   else if (answer.equalsIgnoreCase("n")) {
            return false;
        } else {
            isAlright();
        }
        return true;
    }

    public static void waitForInput()  {
        System.out.println();
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public static void cls() throws IOException, InterruptedException   {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
        Process process = processBuilder.inheritIO().start();
        process.waitFor();
    }


}
