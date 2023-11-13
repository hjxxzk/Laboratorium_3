package ui;

import opinion.Type;

import java.util.Date;

public interface UInterface {
    void displayMenu();
    void startSystem();
    void stopSystem();
    void getUserInput();
    void getOpinion();
    int getIntData();
    String getStringData();
    int setInt(String data);
    Date setDate();

    Type setType();
    int setWeight();
    String setComment();
    boolean isAlright(String data);
    void getTrend();
}
