package logic;

import opinion.Opinion;
import opinion.Type;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class SQLiteWorker {

    private static Connection connect(String dbPath) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

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

    static void cutOpinion(int id, int index, String dbPath, String dbName) throws SQLException, ClassNotFoundException {
        Connection connection = connect(dbPath);
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM " + dbName + " WHERE id = " + id + " AND number = " + index);
        deleteStatement.executeUpdate();
    }
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
