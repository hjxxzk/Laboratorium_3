package org.example;

import logic.CompanyFeedback;
import ui.CMDInterface;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        String dbPath = "C:/Users/agnie/IdeaProjects/Laboratorium_3/Laboratorium_3/src/main/resources/Lab3.db";
        String dbName = "lab3";
        String pyPath = "C:/Users/agnie/IdeaProjects/Laboratorium_3/Laboratorium_3/src/main/resources/TrendLineMaker.py";
        CompanyFeedback feedback = new CompanyFeedback(dbPath, dbName, pyPath);
        CMDInterface CMDin = new CMDInterface(feedback, dbPath);

        CMDin.startSystem();
        CMDin.displayMenu();
    }
}