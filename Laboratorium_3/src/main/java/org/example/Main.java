package org.example;

import logic.CompanyFeedback;
import ui.CMDInterface;

public class Main {
    public static void main(String[] args) {
        CompanyFeedback feedback = new CompanyFeedback();
        CMDInterface CMDin = new CMDInterface(feedback);

        CMDin.startSystem();
        CMDin.displayMenu();
    }
}