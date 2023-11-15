package org.example;

import logic.CompanyFeedback;
import ui.CMDInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dbPath = "--tu wstaw ścieżkę do bazy danych ---";
        String dbName = "lab3";
        CompanyFeedback feedback = new CompanyFeedback(dbPath, dbName);
        CMDInterface CMDin = new CMDInterface(feedback);

        CMDin.startSystem();
        CMDin.displayMenu();
    }
}