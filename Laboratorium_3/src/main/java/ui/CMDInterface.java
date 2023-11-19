package ui;

import logic.CompanyFeedback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import static ui.Setters.*;

public class CMDInterface implements UInterface {

    private final CompanyFeedback feedback;
    private final String dbPath;
    Scanner scanner = new Scanner(System.in);
    public CMDInterface(CompanyFeedback feedback, String dbPath)   {
        this.feedback = feedback;
        this.dbPath = dbPath;
    }


    @Override
    public void displayMenu() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        cls();
        System.out.println("How can I be of service?");
        System.out.println("1. Add opinion");
        System.out.println("2. Find employee");
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
    public void getUserInput() throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        System.out.println("Enter number of action: ");
        int input = getIntData();
        if(isAlright()) {

            switch (input) {
                case 1 -> { getOpinion(); waitForInput(); displayMenu(); }
                case 2 -> { feedback.displayOpinion(setInt("ID")); waitForInput(); displayMenu(); }
                case 3 -> { feedback.cancelOpinion(setInt("ID"), setInt("index of a comment")); waitForInput(); displayMenu(); }
                case 4 -> { getTrend(); waitForInput(); displayMenu(); }
                case 5 -> { feedback.displayAll(); waitForInput(); displayMenu(); }
                case 6 -> { stopSystem(); scanner.close(); }
                default -> { System.out.print("Incorrect number. "); getUserInput(); }
            }

        } else {
            getUserInput();
        }
    }

    @Override
    public void getOpinion() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        feedback.addOpinion(setInt("ID"),setDate(),setType(),setWeight(),setComment());
    }

    @Override
    public void getTrend() throws IOException, InterruptedException {

        String id = String.valueOf(setInt("ID"));

        System.out.println("Set start of the period to create trend line for.");
        LocalDate start = setDate();

        System.out.println("Set end of the period to create trend line for.");
        LocalDate end = setDate();

        feedback.analyzeTrend(id, String.valueOf(start), String.valueOf(end), dbPath);

    }

}
