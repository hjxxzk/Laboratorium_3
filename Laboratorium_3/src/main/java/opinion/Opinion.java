package opinion;

import java.time.LocalDate;

public class Opinion {

    private final int id;

    private final int number;
    private final LocalDate date;
    private final int weight;
    private final String comment;
    Type type;

    public Opinion(int id, LocalDate date, int number, Type type, int weight, String comment) {

        this.id = id;
        this.date = date;
        this.number = number;
        this.type = type;
        this.weight = weight;
        this.comment = comment;

    }

    public String getDate() {
        return date.toString();
    }

    public int getWeight() { return weight; }

    public String getComment() {
        return comment;
    }

    public Type getType() {
        return type;
    }

    public int getId()  {
        return id;
    }
    public int getNumber() { return number; }
}
