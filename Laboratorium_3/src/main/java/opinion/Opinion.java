package opinion;

import java.time.LocalDate;
/**
 * The `Opinion` class represents an opinion in the company feedback system.
 */
public class Opinion {
    /**
     * Identification of an employee
     */
    private final int id;
    /**
     * Identification of a specific comment
     */
    private final int number;
    /**
     * Date when the opinion was submitted
     */
    private final LocalDate date;
    /**
     * Weight assigned to the opinion
     */
    private final int weight;
    /**
     * Comment provided in the opinion
     */
    private final String comment;
    /**
     * Type of the opinion (e.g., positive, negative)
     */
    Type type;
    /**
     * Constructor to initialize an Opinion object with specified attributes.
     *
     * @param id      The unique identifier for the opinion.
     * @param date    The date when the opinion was submitted.
     * @param number  The unique number associated with the opinion.
     * @param type    The type of the opinion (e.g., positive, negative).
     * @param weight  The weight assigned to the opinion.
     * @param comment The comment provided in the opinion.
     */
    public Opinion(int id, LocalDate date, int number, Type type, int weight, String comment) {

        this.id = id;
        this.date = date;
        this.number = number;
        this.type = type;
        this.weight = weight;
        this.comment = comment;

    }
    /**
     * Gets the date of the opinion as a string.
     * @return The date of the opinion.
     */
    public String getDate() {
        return date.toString();
    }
    /**
     * Gets the weight assigned to the opinion.
     * @return The weight of the opinion.
     */
    public int getWeight() { return weight; }
    /**
     * Gets the comment provided in the opinion.
     * @return The comment in the opinion.
     */
    public String getComment() {
        return comment;
    }
    /**
     * Gets the type of the opinion.
     * @return The type of the opinion (e.g., positive, negative).
     */
    public Type getType() {
        return type;
    }
    /**
     * Gets the unique identifier of the opinion.
     * @return The unique identifier of the opinion.
     */
    public int getId()  {
        return id;
    }
    /**
     * Gets the unique number associated with the opinion.
     * @return The unique number associated with the opinion.
     */
    public int getNumber() { return number; }
}
