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
    int getIntData();
    String getStringData();
    int setInt(String data);
    LocalDate setDate();

    Type setType();
    int setWeight();
    String setComment();
    boolean isAlright(String data);
    void getTrend();
}
