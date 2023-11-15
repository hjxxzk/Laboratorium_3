package ui;

import opinion.Type;

import java.io.IOException;
import java.time.LocalDate;

public interface UInterface {
    void displayMenu() throws IOException, InterruptedException;
    void startSystem();
    void stopSystem();
    void getUserInput() throws IOException, InterruptedException;
    void getOpinion();
    void getTrend();
}
