package ui;

import logic.CompanyFeedback;

import java.io.IOException;
import java.util.Scanner;

import static ui.Setters.*;

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
        if(isAlright()) {

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
            getUserInput();
        }
    }

    @Override
    public void getOpinion() {
        feedback.addOpinion(setInt("ID"),setDate(),setType(),setWeight(),setComment());
    }



    public void cls() throws IOException, InterruptedException   {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    @Override
    public void getTrend() {

    }
}
