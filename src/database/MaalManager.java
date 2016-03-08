package database;

import javafx.scene.image.Image;
import treningsdagbok.Ovelse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArntKristoffer on 08.03.2016.
 */
public class MaalManager {

    private Connection connection;

    public MaalManager(Connection connection)
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

    public boolean addMaal(int maalNr, int OvelseNr, Boolean kondis, Date dato, Time tid)
    {
        String sql = "INSERT INTO Ovelse VALUES ( " + maalNr + ", '" + OvelseNr + "', '" + kondis + ", " + dato + ", " + tid +"');";
        return updateSQL(sql);
    }

    public boolean editStyrke(int maalNr, int ovelseNr, int sett, int repitisjoner, int belastning)
    {
        String sql = "UPDATE Ovelse " +
                "SET sett = '" + sett + "', repitisjoner = '" + repitisjoner + "' " + "', belastning = '" + belastning+
                "WHERE maalNr = " + maalNr + ";";
        return updateSQL(sql);
    }

    public boolean editOvelse(String gammeltNavn, String nyttNavn, String nyBeskrivelse)
    {
        String sql = "UPDATE Ovelse " +
                "SET navn = '" + nyttNavn + "', beskrivelse = '" + nyBeskrivelse + "' " +
                "WHERE navn = '" + gammeltNavn + "';";
        return updateSQL(sql);
    }

    public boolean deleteOvelse(int ovelseNr)
    {
        String sql = "DELETE FROM Ovelse WHERE ovelseNr = " + ovelseNr + ";";
        return updateSQL(sql);
    }

    public boolean deleteOvelse(String navn)
    {
        String sql = "DELETE FROM Ovelse WHERE navn = " + navn + ";";
        return updateSQL(sql);
    }

    public Ovelse getOvelse(String navn)
    {
        String sql = "SELECT * FROM Ovelse WHERE navn = '" + navn + "';";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                int ovelseNr = res.getInt(1);
                String beskrivelse = res.getString(3);
                return new Ovelse(ovelseNr, navn, beskrivelse);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ovelse> getOvelser()
    {
        List<Ovelse> ovelser = new ArrayList<>();
        String sql = "SELECT * FROM Ovelse;";
        try (ResultSet res = connection.createStatement().executeQuery(sql))
        {
            while (res.next())
            {
                int ovelseNr = res.getInt(1);
                String navn = res.getString(2);
                String beskrivelse = res.getString(3);
                ovelser.add(new Ovelse(ovelseNr, navn, beskrivelse));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ovelser;
    }

}