package logic;

import opinion.Opinion;
import opinion.Type;

import java.time.LocalDate;
import java.util.ArrayList;

import static logic.SQLiteWorker.readDatabase;

public class CompanyFeedback implements LogicInterface {

    private final ArrayList<Opinion> opinions;

    public CompanyFeedback(String dbPath, String dbName)    {
        this.opinions = readDatabase(dbPath, dbName);
    }

    @Override
    public void displayOpinion(int id) {
        opinions.stream()
                .filter(Opinion -> Opinion.getId() == id)
                .forEach(Opinion -> {
                    System.out.println();
                    System.out.println("ID " + Opinion.getId() + "Date: " + Opinion.getDate());
                    System.out.println("Type: " + Opinion.getType() + "   Weight: " + Opinion.getWeight() + "   Index: " + Opinion.getNumber());
                    System.out.println(Opinion.getComment());
                });
    }

    @Override
    public void displayAll() {
        opinions
                .forEach(Opinion -> {
                    System.out.println();
                    System.out.println("ID " + Opinion.getId() + "  Date: " + Opinion.getDate());
                    System.out.println("Type: " + Opinion.getType() + "   Weight: " + Opinion.getWeight() + "   Index: " + Opinion.getNumber());
                    System.out.println(Opinion.getComment());
                });
    }

    @Override
    public void addOpinion(int id, LocalDate date, Type type, int weight, String comment) {

        Opinion opinion = new Opinion(id, date, setOrder(id), type, weight, comment);
        opinions.add(opinion);
    }
    @Override
    public int setOrder(int id) {
        return (int) opinions.stream()
                    .filter(Opinion -> Opinion.getId() == id)
                    .count() + 1;
    }

    @Override
    public void cancelOpinion(int id, int number) {
        opinions.removeIf(Opinion -> Opinion.getId() == id && Opinion.getNumber() == number);
    }

    @Override
    public void analyzeTrend(String period) {

    }
}
