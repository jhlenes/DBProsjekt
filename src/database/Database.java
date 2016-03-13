package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database
{
    private Connection connection;

    private OvelseManager ovelseManager;
    private MaalManager maalManager;
    private TreningsoktManager treningsoktManager;
    private ResultatManager resultatManager;

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
            resultatManager = new ResultatManager(connection);
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

    public TreningsoktManager getTreningsoktManager()
    {
        return treningsoktManager;
    }

    public ResultatManager getResultatManager()
    {
        return resultatManager;
    }

    public static boolean updateSQL(String sql, Connection connection)
    {
        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
