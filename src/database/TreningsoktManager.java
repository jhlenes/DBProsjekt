package database;


import treningsdagbok.Ovelse;
import treningsdagbok.Treningsokt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Not functional!!!!!!!!!!!!!!!!!!!!!!!
 */

public class TreningsoktManager
{
    private Connection connection;

    public TreningsoktManager(Connection connection)
    {
        this.connection = connection;
    }

    private boolean updateSQL(String sql)
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

    public boolean loadOvelserToTreningsokt(Treningsokt treningsokt)
    {
        int oktNr = treningsokt.getOktNr();
        String sql = "SELECT * FROM Treningsokt_har_ovelse WHERE oktNr = " + oktNr + ";";
        try (ResultSet res = connection.createStatement().executeQuery(sql))
        {
            while (res.next())
            {
                int ovelseNr = res.getInt(1);
                String navn = res.getString(2);
                String beskrivelse = res.getString(3);
                treningsokt.addOvelse(new Ovelse(ovelseNr, navn, beskrivelse));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addTreningsokt(Treningsokt treningsokt)
    {
        String sql = "INSERT INTO Treningsokt (oktNr, dato, tidspunkt, varighet,) VALUES ()";
        return false;
    }

    public static void main(String[] args)
    {

    }


}
