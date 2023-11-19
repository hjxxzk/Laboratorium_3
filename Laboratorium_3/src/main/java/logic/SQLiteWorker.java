package logic;

import opinion.Opinion;
import opinion.Type;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 * The `SQLiteWorker` class provides methods for database connectivity and operations related to the SQLite database.
 */
public class SQLiteWorker {

    /**
     * Establishes a connection to the SQLite database.
     *
     * @param dbPath The path to the SQLite database.
     * @return A connection to the SQLite database.
     * @throws SQLException           If a SQL exception occurs during database connection.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    private static Connection connect(String dbPath) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    /**
     * Reads opinions from the SQLite database and returns them as an ArrayList.
     *
     * @param dbPath The path to the SQLite database.
     * @param dbName The name of the SQLite database table.
     * @return An ArrayList containing opinions retrieved from the database.
     */
    static ArrayList<Opinion> readDatabase(String dbPath, String dbName)    {
        ArrayList<Opinion> opinions = new ArrayList<>();

        try ( Connection connection = connect(dbPath);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + dbName.toLowerCase());
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next())    {
                opinions.add(new Opinion(resultSet.getInt("id"),
                                        convertDate(resultSet.getString("date")),
                                        resultSet.getInt("number"),
                                        Type.valueOf(resultSet.getString("type")),
                                        resultSet.getInt("weight"),
                                        resultSet.getString("comment")));
            }

        }   catch (SQLException | ClassNotFoundException e)  {
            e.printStackTrace();
        }

        return opinions;
    }

    /**
     * Converts a date string from the database to a LocalDate object.
     *
     * @param date The date string to be converted.
     * @return The LocalDate representation of the date string.
     */
    public static LocalDate convertDate(String date) {
        LocalDate output = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            output = LocalDate.parse(date, formatter);

        } catch (DateTimeParseException e) {
            System.out.println("Parsing error");
        }
        return output;
    }

    /**
     * Deletes a specific opinion from the SQLite database based on its unique identifier and order/index.
     *
     * @param id     The unique identifier of the opinion to be deleted.
     * @param index  The order/index of the opinion to be deleted.
     * @param dbPath The path to the SQLite database.
     * @param dbName The name of the SQLite database table.
     * @throws SQLException           If a SQL exception occurs during database interaction.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    static void cutOpinion(int id, int index, String dbPath, String dbName) throws SQLException, ClassNotFoundException {
        Connection connection = connect(dbPath);
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM " + dbName + " WHERE id = " + id + " AND number = " + index);
        deleteStatement.executeUpdate();
    }

    /**
     * Inserts a new opinion into the SQLite database.
     *
     * @param opinion The new opinion to be inserted.
     * @param dbPath  The path to the SQLite database.
     * @param dbName  The name of the SQLite database table.
     * @throws SQLException           If a SQL exception occurs during database interaction.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    static void newOpinion(Opinion opinion, String dbPath, String dbName) throws SQLException, ClassNotFoundException {
        Connection connection = connect(dbPath);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + dbName + " (id, date, number, type, weight, comment) VALUES (?, ?, ?, ?, ?, ?)");

        statement.setInt(1, opinion.getId());
        statement.setString(2, opinion.getDate());
        statement.setInt(3, opinion.getNumber());
        statement.setString(4, String.valueOf(opinion.getType()));
        statement.setInt(5, opinion.getWeight());
        statement.setString(6, opinion.getComment());

        statement.executeUpdate();

    }

}
