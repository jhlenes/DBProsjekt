package database;

/**
 * Created by Christian on 10.03.2016.
 */

import treningsdagbok.Resultater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultatManager
{
    private Connection connection;

    public ResultatManager(Connection connection)
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

    public boolean addResultat(int oktNr, int ovelseNr, int belastning, int sett, int repitisjoner)
    {
        String sql = "INSERT INTO Resultat VALUES ('" + belastning + "','" + repitisjoner + "','" + sett + "','" + oktNr + "','" + ovelseNr + "');";
        return updateSQL(sql);
    }

    public boolean deleteResultat(int oktNr, int ovelseNr)
    {
        String sql = "DELETE FROM Resultat WHERE oktNr = '" + oktNr + "' AND ovelseNr = '" + ovelseNr + "';";
        return updateSQL(sql);
    }

    public List<Resultater> getResultat(int ovelseNr)
    {
        List<Resultater> resultater = new ArrayList<>();
        String sql = "SELECT * FROM Resultat WHERE ovelseNr = " + ovelseNr + ";";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            while (res.next())
            {
                int belastning = res.getInt(1);
                int repitisjoner = res.getInt(2);
                int sett = res.getInt(3);
                int oktNr = res.getInt(4);
                int ovelseNr2 = res.getInt(5);
                resultater.add(new Resultater(ovelseNr2, belastning, sett, repitisjoner, oktNr));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultater;
    }

    public List<Resultater> getToppTiResultat(int ovelseNr)
    {
        List<Resultater> resultater = new ArrayList<>();
        String sql = "SELECT * FROM Resultat WHERE ovelseNr = '" + ovelseNr + "' ORDER BY belastning DESC LIMIT 10;";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            while (res.next())
            {
                int belastning = res.getInt(1);
                int repitisjoner = res.getInt(2);
                int sett = res.getInt(3);
                int oktNr = res.getInt(4);
                int ovelseNr2 = res.getInt(5);
                resultater.add(new Resultater(ovelseNr2, belastning, sett, repitisjoner, oktNr));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultater;
    }


}
