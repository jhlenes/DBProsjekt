package connection;

import java.sql.*;
import java.util.Properties;

public class DBConnection
{

    private Connection connection;

    public DBConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Properties p = new Properties();
            p.put("user", "janhle_prosjekt");
            p.put("password", "#CantGuessThis");
            connection = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/janhle_treningsdagbok", p);

        } catch (Exception e)
        {
            throw new RuntimeException("***** Unable to connect to database *****");
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public static void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        } catch (SQLException e)
        {
            // Whatever
        }
    }

    public static void closeStatement(Statement statement)
    {
        try
        {
            statement.close();
        } catch (SQLException e)
        {
            // Whatever
        }
    }

    public static void closeResultSet(ResultSet resultSet)
    {
        try
        {
            resultSet.close();
        } catch (SQLException e)
        {
            // Whatever
        }
    }

    public static void main(String[] args) throws SQLException
    {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();

        // Delete
        statement.executeUpdate("DELETE FROM Ovelse Where TRUE;");

        // Update
        statement.executeUpdate("INSERT INTO Ovelse (navn, beskrivelse) VALUES ('Beinpress','');");
        statement.executeUpdate("INSERT INTO Ovelse (navn, beskrivelse) VALUES ('Benkpress','');");
        statement.executeUpdate("INSERT INTO Ovelse (navn, beskrivelse) VALUES ('Armhevninger','');");
        statement.executeUpdate("INSERT INTO Ovelse (navn, beskrivelse) VALUES ('Knebøy','');");
        statement.executeUpdate("INSERT INTO Ovelse (navn, beskrivelse) VALUES ('Markløft','');");

        // Select
        ResultSet res = statement.executeQuery("SELECT * FROM Ovelse;");

        // Show data
        while (res.next())
        {
            System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3));
        }

        // Close
        connection.close();
        statement.close();
        res.close();
    }

}
