package database;

/**
 * Created by Christian on 10.03.2016.
 */

import treningsdagbok.OvelseResultat;
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
        String sql = "SELECT * FROM Resultat WHERE ovelseNr = " + ovelseNr + ";";
        return selectResultater(sql);
    }

    public List<Resultater> getToppTiResultat(int ovelseNr)
    {
        String sql = "SELECT * FROM Resultat WHERE ovelseNr = '" + ovelseNr + "' ORDER BY belastning DESC LIMIT 10;";
        return selectResultater(sql);
    }

    private List<Resultater> selectResultater(String sql)
    {
        List<Resultater> resultater = new ArrayList<>();
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            while (res.next())
            {
                int belastning = res.getInt(1);
                int repitisjoner = res.getInt(2);
                int sett = res.getInt(3);
                int oktNr = res.getInt(4);
                int ovelseNr = res.getInt(5);
                resultater.add(new Resultater(ovelseNr, belastning, sett, repitisjoner, oktNr));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultater;
    }

    public OvelseResultat getOvelseResultat(int oktNr, int ovelseNr)
    {
        String sql = "SELECT navn, sett, repetisjoner, belastning FROM Resultat INNER JOIN Ovelse ON Resultat.ovelseNr = Ovelse.ovelseNr" +
                " WHERE Resultat.oktNr = " + oktNr + " AND Resultat.ovelseNr = " + ovelseNr + ";";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                String navn = res.getString(1);
                int sett = res.getInt(2);
                int repitisjoner = res.getInt(3);
                int belastning = res.getInt(4);
                return new OvelseResultat(oktNr, ovelseNr, navn, sett, repitisjoner, belastning);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
