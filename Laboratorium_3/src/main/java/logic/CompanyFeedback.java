package logic;

import opinion.Opinion;
import opinion.Type;

import java.util.ArrayList;
import java.util.Date;

public class CompanyFeedback implements LogicInterface {

    private final ArrayList<Opinion> opinions;

    public CompanyFeedback()    {
        this.opinions = new ArrayList<>();
    }

    @Override
    public void displayOpinion(int id) {
        opinions.stream()
                .filter(Opinion -> Opinion.getId() == id)
                .forEach(Opinion -> {   //metoda dla display
                    System.out.println("Date: " + Opinion.getDate());
                    System.out.println("Type: " + Opinion.getType() + "   Weight: " + Opinion.getWeight() + "   Index: " + Opinion.getNumber());
                    System.out.println(Opinion.getComment());
                    System.out.println();
                });
    }

    @Override
    public void addOpinion(int id, Date date, Type type, int weight, String comment) {

        Opinion opinion = new Opinion(id, date, type, weight, comment, setOrder(id));
        opinions.add(opinion);
    }

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
