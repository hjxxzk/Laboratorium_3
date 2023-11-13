package opinion;

import java.util.Date;

public class Opinion {

    private final int id;

    private final int number;
    private final Date date;
    private final int weight;
    private final String comment;
    Type type;

    public Opinion(int id, Date date, Type type, int weight, String comment, int number) {

        this.id = id;
        this.date = date;
        this.type = type;
        this.weight = weight;
        this.comment = comment;
        this.number = number;

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
