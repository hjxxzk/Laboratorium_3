package logic;

import opinion.Type;

import java.sql.SQLException;
import java.time.LocalDate;
/**
 * The `LogicInterface` interface defines the contract for handling business logic related to company feedback.
 */
public interface LogicInterface {

    /**
     * Displays a specific opinion based on its unique identifier.
     *
     * @param id The unique identifier of the opinion to be displayed.
     */
    void displayOpinion(int id);


    /**
     * Displays all opinions, sorted by ID and then by opinion number.
     */
    void displayAll();


    /**
     * Adds a new opinion to the system and updates the database.
     *
     * @param id      The unique identifier for the new opinion.
     * @param date    The date when the opinion is submitted.
     * @param type    The type of the opinion (e.g., positive, negative).
     * @param weight  The weight assigned to the opinion.
     * @param comment The comment provided in the opinion.
     * @throws SQLException           If a SQL exception occurs during database interaction.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    void addOpinion(int id, LocalDate date, Type type, int weight, String comment) throws SQLException, ClassNotFoundException;


    /**
     * Sets the order/index for a new opinion based on the existing opinions with the same ID.
     *
     * @param id The unique identifier of the opinion.
     * @return The order/index for the new opinion.
     */
    int setOrder(int id);


    /**
     * Cancels a specific opinion based on its unique identifier and order/index.
     *
     * @param id     The unique identifier of the opinion to be canceled.
     * @param number The order/index of the opinion to be canceled.
     * @throws SQLException           If a SQL exception occurs during database interaction.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    void cancelOpinion(int id, int number) throws SQLException, ClassNotFoundException;


    /**
     * Analyzes the trend using a Python script based on the provided parameters.
     *
     * @param id     The identifier for the trend analysis.
     * @param start  The start date for the trend analysis.
     * @param end    The end date for the trend analysis.
     * @param dbPath The path to the SQLite database.
     */
    void analyzeTrend(String id, String start, String end, String dbPath);
}
