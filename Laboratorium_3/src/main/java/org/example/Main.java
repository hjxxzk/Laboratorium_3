package org.example;

import logic.CompanyFeedback;
import ui.CMDInterface;

import java.io.IOException;
import java.sql.SQLException;
/**
 * Main class
 */
public class Main {
    /**
     * The main method that initializes and starts the company feedback system.
     *
     * @param args Command-line arguments containing the database path, database name, and Python script path.
     * @throws IOException            If an I/O exception occurs.
     * @throws InterruptedException   If the execution is interrupted.
     * @throws SQLException           If a SQL exception occurs.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    public static void main(String[] args) throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        String dbPath = args[0];
        String dbName = args[1];
        String pyPath = args[2];
        CompanyFeedback feedback = new CompanyFeedback(dbPath, dbName, pyPath);
        CMDInterface CMDin = new CMDInterface(feedback, dbPath);

        CMDin.startSystem();
        CMDin.displayMenu();
    }
}