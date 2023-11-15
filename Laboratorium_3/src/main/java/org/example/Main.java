package org.example;

import logic.CompanyFeedback;
import ui.CMDInterface;

import java.io.IOException;
//C:/Users/agnie/IdeaProjects/Laboratorium_3/Laboratorium_3/src/main/resources/Lab3.db
//Lab3
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dbPath = args[0];
        String dbName = args[1];
        CompanyFeedback feedback = new CompanyFeedback(dbPath, dbName);
        feedback.displayOpinion(15);
        CMDInterface CMDin = new CMDInterface(feedback);

        CMDin.startSystem();
        CMDin.displayMenu();
    }
}