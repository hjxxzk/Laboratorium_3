package ui;

import java.io.IOException;
import java.sql.SQLException;
/**
 * The `UInterface` interface defines the contract for the user interface in the company feedback system.
 */
public interface UInterface {

    /**
     * Displays the main menu of the command-line interface.
     *
     * @throws IOException            If an I/O exception occurs.
     * @throws InterruptedException   If the execution is interrupted.
     * @throws SQLException           If a SQL exception occurs.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    void displayMenu() throws IOException, InterruptedException, SQLException, ClassNotFoundException;

    /**
     * Displays a welcome message to start the system.
     */
    void startSystem();

    /**
     * Displays a closing message to end the system.
     */
    void stopSystem();

    /**
     * Gets user input based on the selected action and performs the corresponding operation.
     *
     * @throws IOException            If an I/O exception occurs.
     * @throws InterruptedException   If the execution is interrupted.
     * @throws SQLException           If a SQL exception occurs.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    void getUserInput() throws IOException, InterruptedException, SQLException, ClassNotFoundException;

    /**
     * Prompts the user for input and adds a new opinion based on the provided data.
     *
     * @throws SQLException           If a SQL exception occurs during database interaction.
     * @throws ClassNotFoundException If the specified class cannot be found.
     * @throws IOException            If an I/O exception occurs.
     * @throws InterruptedException   If the execution is interrupted.
     */
    void getOpinion() throws SQLException, ClassNotFoundException, IOException, InterruptedException;

    /**
     * Prompts the user for input and analyzes the trend based on the provided parameters.
     *
     * @throws IOException        If an I/O exception occurs.
     * @throws InterruptedException If the execution is interrupted.
     */
    void getTrend() throws IOException, InterruptedException;

}
