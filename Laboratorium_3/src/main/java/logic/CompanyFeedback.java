package logic;

import opinion.Opinion;
import opinion.Type;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import static logic.SQLiteWorker.*;
/**
 * The `CompanyFeedback` class implements the `LogicInterface` and represents the logic layer of the company feedback system.
 */

public class CompanyFeedback implements LogicInterface {
    /**
     * Collection to store opinions
     */
    private final ArrayList<Opinion> opinions;
    /**
     * Database path
     */
    private final String dbPath;
    /**
     * Database name
     */
    private final String dbName;
    /**
     * Path to the Python script for trend analysis
     */
    private final String pyPath;

    /**
     * Constructor to initialize a CompanyFeedback object with the specified database path, database name, and Python script path.
     */
    public CompanyFeedback(String dbPath, String dbName, String pyPath)    {
        this.dbPath = dbPath;
        this.opinions = readDatabase(dbPath, dbName);
        this.dbName = dbName.toLowerCase();
        this.pyPath = pyPath;
    }

    /**
     * Displays a specific opinion based on its unique identifier.
     */
    @Override
    public void displayOpinion(int id) {
        opinions.stream()
                .filter(Opinion -> Opinion.getId() == id)
                .sorted(Comparator.comparingInt(Opinion::getNumber))
                .forEach(Opinion -> {
                    System.out.println();
                    System.out.println("ID " + Opinion.getId() + "   Date: " + Opinion.getDate());
                    System.out.println("Type: " + Opinion.getType() + "   Weight: " + Opinion.getWeight() + "   Index: " + Opinion.getNumber());
                    System.out.println(Opinion.getComment());
                });
    }

    /**
     * Displays all opinions, sorted by ID and then by opinion number.
     */
    @Override
    public void displayAll() {
        opinions.stream()
                .sorted(Comparator.comparingInt(Opinion::getId)
                        .thenComparingInt(Opinion::getNumber))
                .forEach(Opinion -> {
                    System.out.println();
                    System.out.println("ID " + Opinion.getId() + "  Date: " + Opinion.getDate());
                    System.out.println("Type: " + Opinion.getType() + "   Weight: " + Opinion.getWeight() + "   Index: " + Opinion.getNumber());
                    System.out.println(Opinion.getComment());
                });
    }

    /**
     * Adds a new opinion to the system and updates the database.
     */
    @Override
    public void addOpinion(int id, LocalDate date, Type type, int weight, String comment) throws SQLException, ClassNotFoundException {

        Opinion opinion = new Opinion(id, date, setOrder(id), type, weight, comment);
        opinions.add(opinion);
        newOpinion(opinion, dbPath, dbName);
    }

    /**
     * Sets the order/index for a new opinion based on the existing opinions with the same ID.
     */
    @Override
    public int setOrder(int id) {
        return (int) opinions.stream()
                    .filter(Opinion -> Opinion.getId() == id)
                    .count() + 1;
    }

    /**
     * Cancels a specific opinion based on its unique identifier and order/index.
     */
    @Override
    public void cancelOpinion(int id, int number) throws SQLException, ClassNotFoundException {
        opinions.removeIf(Opinion -> Opinion.getId() == id && Opinion.getNumber() == number);
        cutOpinion(id, number, dbPath, dbName);

    }

    /**
     * Analyzes the trend using a Python script based on the provided parameters.
     */
    @Override
    public void analyzeTrend(String id, String start, String end, String dbPath) {

        try {
            String[] command = {"python", pyPath, id, start, end, dbPath};
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

        } catch (IOException | InterruptedException e)  {
            e.printStackTrace();
        }


    }
}
