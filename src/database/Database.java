package database;

import treningsdagbok.Treningsokt;

import java.sql.*;
import java.util.Properties;

public class Database
{

    private Connection connection;
    private OvelseManager ovelseManager;
    private MaalManager maalManager;
    private TreningsoktManager treningsoktManager;

    public Database()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Properties p = new Properties();
            p.put("user", "janhle_prosjekt");
            p.put("password", "#CantGuessThis");
            connection = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/janhle_treningsdagbok", p);

            // Setup managers
            ovelseManager = new OvelseManager(connection);
            maalManager = new MaalManager(connection);
            treningsoktManager = new TreningsoktManager(connection);


        } catch (Exception e)
        {
            throw new RuntimeException("***** Unable to connect to database *****");
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public OvelseManager getOvelseManager()
    {
        return ovelseManager;
    }

    public MaalManager getMaalManager()
    {
        return maalManager;
    }


    /**
     *  Example code
     */
    public static void main(String[] args) throws SQLException
    {
        Database database = new Database();
        Connection connection = database.getConnection();
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
