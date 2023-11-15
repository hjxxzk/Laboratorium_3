package ui;

import logic.CompanyFeedback;
import opinion.Type;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CMDInterface implements UInterface {

    private final CompanyFeedback feedback;
    Scanner scanner = new Scanner(System.in);
    public CMDInterface(CompanyFeedback feedback)   {
        this.feedback = feedback;
    }


    @Override
    public void displayMenu() throws IOException, InterruptedException {
      //  cls();
        System.out.println("How can I be of service?");
        System.out.println("1. Add opinion");
        System.out.println("2. Find opinion");
        System.out.println("3. Remove opinion");
        System.out.println("4. Analyze trend");
        System.out.println("5. Display all");
        System.out.println("6. End for today");
        getUserInput();
    }

    @Override
    public void startSystem() {
        System.out.println("Welcome! Let's get to work, boss!");
    }

    @Override
    public void stopSystem() {
        System.out.println("Thanks for the hard work. Bye!");
    }

    @Override
    public void getUserInput() throws IOException, InterruptedException {

        System.out.println("Enter number of action: ");
        int input = getIntData();
        if(isAlright(String.valueOf(input))) {

            switch (input) {
                case 1 -> { getOpinion(); scanner.nextLine(); displayMenu(); }
                case 2 -> { feedback.displayOpinion(setInt("ID")); displayMenu(); }
                case 3 -> { feedback.cancelOpinion(setInt("ID"), setInt("index of a comment")); displayMenu(); }
                case 4 -> System.out.println("4");
                case 5 -> { feedback.displayAll(); scanner.nextLine(); displayMenu(); }
                case 6 -> { stopSystem(); scanner.close(); }
                default -> { System.out.print("Incorrect number. "); getUserInput(); }
            }

        } else {
            getIntData();
        }
    }

    @Override
    public void getOpinion() {
        feedback.addOpinion(setInt("ID"),setDate(),setType(),setWeight(),setComment());
    }

    @Override
    public int getIntData() {
        int input = 0;
        try {
            input = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException exception)  {
            System.out.print("Incorrect input. Try again. ");
            getIntData();
        }
        return input;
    }

    public String getStringData() {
        return scanner.nextLine();
    }

    public int setInt(String data)   {
        System.out.println("Enter " + data + ": ");
        int input = getIntData();

        if (isAlright(String.valueOf(input)))
            return input;
        else
            setInt(data);

        return input;
    }


    public LocalDate setDate() {

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

        if (isAlright(String.valueOf(date)))
            return date;
        else
            setDate();

        return date;
    }

    public Type setType() {

        System.out.println("Negative or positive comment? Type p or n: ");
        String input = getStringData();

        if (input.equalsIgnoreCase("p")) {
            return Type.Positive;
        }   else if (input.equalsIgnoreCase("n")) {
            return Type.Negative;
        }
        return setType();
    }

    public int setWeight()  {

        System.out.println("Set weight (1 / 2 / 3). Enter number: ");
        int weight = getIntData();
        if (weight > 0 && weight < 4) {
            if (isAlright(String.valueOf(weight)))
                return weight;
             else
                setWeight();
        } else {
            System.out.println("Incorrect value. ");
        }
        return setWeight();
    }

    public String setComment()  {

        System.out.println("Enter a comment (max. 350 characters)");
        String comment = getStringData();

        if (comment.length() > 350) {
            System.out.println("Input too long. Try again.");
            setComment();
        }

        if (isAlright(comment))
            return comment;
        else
            setComment();

        return comment;
    }

    public boolean isAlright(String data)   {
        System.out.println("Is the input correct? Type y or n: ");
        String answer = getStringData();

        if (answer.equalsIgnoreCase("y")) {
            return true;
        }   else if (answer.equalsIgnoreCase("n")) {
            return false;
        } else {
            isAlright(data);
        }
        return true;
    }

    public void cls() throws IOException, InterruptedException   {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    @Override
    public void getTrend() {

    }
}
