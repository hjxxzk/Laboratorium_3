package ui;

import logic.CompanyFeedback;
import logic.LogicInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import static ui.Setters.*;
/**
 * The `CMDInterface` class implements the `UInterface` and provides a command-line interface for interacting with the company feedback system.
 */
public class CMDInterface implements UInterface {
    /**
     * Instance of the CompanyFeedback class to handle the program logic
     */
    private final LogicInterface feedback;
    /**
     * ath to the SQLite database
     */
    private final String dbPath;
    /**
     * Scanner for user input
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor to initialize a CMDInterface object with the specified CompanyFeedback instance and database path.
     *
     * @param feedback The CompanyFeedback instance to handle the business logic.
     * @param dbPath   The path to the SQLite database.
     */
    public CMDInterface(LogicInterface feedback, String dbPath)   {//
        this.feedback = feedback;
        this.dbPath = dbPath;
    }

    /**
     * Displays the main menu of the command-line interface.
     *
     * @throws IOException            If an I/O exception occurs.
     * @throws InterruptedException   If the execution is interrupted.
     * @throws SQLException           If a SQL exception occurs.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
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


    /**
     * Displays a welcome message to start the system.
     */
    @Override
    public void startSystem() {
        System.out.println("Welcome! Let's get to work, boss!");
    }


    /**
     * Displays a closing message to end the system.
     */
    @Override
    public void stopSystem() {
        System.out.println("Thanks for the hard work. Bye!");
    }


    /**
     * Gets user input based on the selected action and performs the corresponding operation.
     */
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

    /**
     * Prompts the user for input and adds a new opinion based on the provided data.
     */
    @Override
    public void getOpinion() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        feedback.addOpinion(setInt("ID"),setDate(),setType(),setWeight(),setComment());
    }

    /**
     * Prompts the user for input and analyzes the trend based on the provided parameters.
     */
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
