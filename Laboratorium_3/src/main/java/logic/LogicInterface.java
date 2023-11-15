package logic;

import opinion.Type;

import java.time.LocalDate;

public interface LogicInterface {
    void displayOpinion(int id);
    void displayAll();
    void addOpinion(int id, LocalDate date, Type type, int weight, String comment);
    int setOrder(int id);
    void cancelOpinion(int id, int number);
    void analyzeTrend(String period);
}
