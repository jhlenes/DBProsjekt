package database;

import treningsdagbok.Ovelse;
import treningsdagbok.OvelseMedResultat;
import treningsdagbok.Resultat;

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

    public boolean addResultat(int oktNr, int ovelseNr, int belastning, int sett, int repitisjoner)
    {
        String sql = "INSERT INTO Resultat VALUES ('" + belastning + "','" + repitisjoner + "','" + sett + "','" + oktNr + "','" + ovelseNr + "');";
        return Database.updateSQL(sql, connection);
    }

    public boolean deleteResultat(int oktNr, int ovelseNr)
    {
        String sql = "DELETE FROM Resultat WHERE oktNr = '" + oktNr + "' AND ovelseNr = '" + ovelseNr + "';";
        return Database.updateSQL(sql, connection);
    }

    public List<Resultat> getResultater(int ovelseNr)
    {
        String sql = "SELECT * FROM Resultat WHERE ovelseNr = " + ovelseNr + ";";
        return selectResultater(sql);
    }

    public List<Resultat> getToppTiResultater(int ovelseNr)
    {
        String sql = "SELECT * FROM Resultat WHERE ovelseNr = '" + ovelseNr + "' ORDER BY belastning DESC LIMIT 10;";
        return selectResultater(sql);
    }

    private List<Resultat> selectResultater(String sql)
    {
        List<Resultat> resultat = new ArrayList<>();
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
                resultat.add(new Resultat(ovelseNr, belastning, sett, repitisjoner, oktNr));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultat;
    }

    public List<Ovelse> getOvelserMedResultater(int oktNr)
    {
        List<Ovelse> ovelseMedResultater = new ArrayList<>();
        String sql = "SELECT Ovelse.ovelseNr, navn, sett, repetisjoner, belastning FROM Ovelse LEFT OUTER JOIN Resultat " +
                "ON Resultat.ovelseNr = Ovelse.ovelseNr AND Resultat.oktNr = " + oktNr + ";";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            while (res.next())
            {
                int ovelseNr = res.getInt(1);
                String navn = res.getString(2);

                int sett = res.getInt(3);
                if (res.wasNull())      // Check if a result is not available
                {
                    ovelseMedResultater.add(new Ovelse(ovelseNr, navn, ""));
                    continue;
                }
                int repitisjoner = res.getInt(4);
                int belastning = res.getInt(5);
                ovelseMedResultater.add(new OvelseMedResultat(oktNr, ovelseNr, navn, sett, repitisjoner, belastning));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ovelseMedResultater;
    }

}
