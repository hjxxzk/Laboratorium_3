package logic;

import opinion.Type;

import java.util.Date;

public interface LogicInterface {
    void displayOpinion(int id);
    void addOpinion(int id, Date date, Type type, int weight, String comment);
    int setOrder(int id);
    void cancelOpinion(int id, int number);
    void analyzeTrend(String period);
}
