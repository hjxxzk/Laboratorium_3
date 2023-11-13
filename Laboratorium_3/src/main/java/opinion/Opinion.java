package opinion;

public class Opinion {

    private int id;
    private String date;
    private int weight;
    private String comment;
    Type type;

    public Opinion(int id, String date, Type type, int weight, String comment) {

        this.id = id;
        this.date = date;
        this.type = type;
        this.weight = weight;
        this.comment = comment;

    }

    public String getDate() {
        return date;
    }

    public int getWeight() {
        return weight;
    }

    public String getComment() {
        return comment;
    }

    public Type getType() {
        return type;
    }

    public int getId()  {
        return id;
    }
}
