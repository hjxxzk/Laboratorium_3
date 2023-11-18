package logic;

import opinion.Opinion;
import opinion.Type;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static logic.SQLiteWorker.*;

public class CompanyFeedback implements LogicInterface {

    private final ArrayList<Opinion> opinions;
    private final String dbPath;
    private final String dbName;
    private final String pyPath;


    public CompanyFeedback(String dbPath, String dbName, String pyPath)    {
        this.dbPath = dbPath;
        this.opinions = readDatabase(dbPath, dbName);
        this.dbName = dbName.toLowerCase();
        this.pyPath = pyPath;
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
    public void addOpinion(int id, LocalDate date, Type type, int weight, String comment) throws SQLException, ClassNotFoundException {

        Opinion opinion = new Opinion(id, date, setOrder(id), type, weight, comment);
        opinions.add(opinion);
        newOpinion(opinion, dbPath, dbName);
    }
    @Override
    public int setOrder(int id) {
        return (int) opinions.stream()
                    .filter(Opinion -> Opinion.getId() == id)
                    .count() + 1;
    }

    @Override
    public void cancelOpinion(int id, int number) throws SQLException, ClassNotFoundException {
        opinions.removeIf(Opinion -> Opinion.getId() == id && Opinion.getNumber() == number);
        cutOpinion(id, number, dbPath, dbName);

    }

    @Override
    public void analyzeTrend(String id, String start, String end, String dbPath) {

        try {
            String[] command = {"python", pyPath, id, start, end, dbPath};
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

        } catch (IOException | InterruptedException e)  {
            e.printStackTrace();
        }


    }
}
