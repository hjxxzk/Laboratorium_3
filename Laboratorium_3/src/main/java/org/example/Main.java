package org.example;

import logic.CompanyFeedback;
import ui.CMDInterface;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
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