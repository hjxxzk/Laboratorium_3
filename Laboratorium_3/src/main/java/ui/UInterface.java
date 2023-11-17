package ui;

import java.io.IOException;
import java.sql.SQLException;


public interface UInterface {
    void displayMenu() throws IOException, InterruptedException, SQLException, ClassNotFoundException;
    void startSystem();
    void stopSystem();
    void getUserInput() throws IOException, InterruptedException, SQLException, ClassNotFoundException;
    void getOpinion() throws SQLException, ClassNotFoundException;
    void getTrend();
}
