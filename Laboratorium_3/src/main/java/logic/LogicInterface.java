package logic;

import opinion.Type;

import java.sql.SQLException;
import java.time.LocalDate;

public interface LogicInterface {
    void displayOpinion(int id);
    void displayAll();
    void addOpinion(int id, LocalDate date, Type type, int weight, String comment) throws SQLException, ClassNotFoundException;
    int setOrder(int id);
    void cancelOpinion(int id, int number) throws SQLException, ClassNotFoundException;
    void analyzeTrend(String id, String start, String end, String dbPath);
}
