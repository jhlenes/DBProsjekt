package database;

import javafx.scene.image.Image;
import treningsdagbok.Maal;
import treningsdagbok.Ovelse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArntKristoffer on 08.03.2016.
 */
public class MaalManager {

    private Connection connection;

    private boolean updateSQL(String sql) {
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

    public MaalManager(Connection connection)
    {
        this.connection = connection;
    }

    public boolean addMaal(Date dato, Time tidspunkt, int sett, int repetisjoner, int belastning, int ovelseNr)
    {
        String sql = "INSERT INTO Maal VALUES (NULL, '" + dato.toString() + "', '" + tidspunkt.toString() + "', "
                + sett + ", " + repetisjoner + ", " + belastning +", " + ovelseNr +");";
        return updateSQL(sql);
    }


    public boolean deleteMaal(int maalNr)
    {
        String sql = "DELETE FROM Maal WHERE maalNr = " + maalNr + ";";
        return updateSQL(sql);
    }


    public Maal getMaal(int maalNr)
    {
        String sql = "SELECT * FROM Maal WHERE maalNr = '" + maalNr + "';";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                int maalNr1 = res.getInt(1);
                int ovelseNr = res.getInt(2);
                Date dato = res.getDate(3);
                Time tidspunkt = res.getTime(4);
                int sett = res.getInt(5);
                int repetisjoner = res.getInt(6);
                int belastning = res.getInt(7);
                return new Maal(maalNr1, ovelseNr, dato, tidspunkt, sett, repetisjoner, belastning);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Maal> getMaal() {
        List<Maal> maal = new ArrayList<>();
        String sql = "SELECT * FROM Maal;";
        try (ResultSet res = connection.createStatement().executeQuery(sql))
        {
            while (res.next())
            {
                int maalNr1 = res.getInt(1);
                int ovelseNr = res.getInt(2);
                Date dato = res.getDate(3);
                Time tidspunkt = res.getTime(4);
                int sett = res.getInt(5);
                int repetisjoner = res.getInt(6);
                int belastning = res.getInt(7);
                maal.add(new Maal(maalNr1, ovelseNr, dato, tidspunkt, sett, repetisjoner, belastning));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return maal;
    }

    public List<Maal> getAlleMaalFor(int ovelseNr) {
        List<Maal> maal = new ArrayList<>();
        String sql = "SELECT * FROM Maal WHERE ovelseNr = " + ovelseNr + ";";
        try (ResultSet res = connection.createStatement().executeQuery(sql))
        {
            while (res.next())
            {
                int maalNr = res.getInt(1);
                Date dato = res.getDate(2);
                Time tidspunkt = res.getTime(3);
                int sett = res.getInt(4);
                int repetisjoner = res.getInt(5);
                int belastning = res.getInt(6);
                maal.add(new Maal(maalNr, ovelseNr, dato, tidspunkt, sett, repetisjoner, belastning));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return maal;
    }

    public Maal getLatest()
    {
        String sql = "SELECT * FROM Maal ORDER BY maalNr DESC LIMIT 1;";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                int maalNr = res.getInt(1);
                Date dato = res.getDate(2);
                Time tidspunkt = res.getTime(3);
                int sett = res.getInt(4);
                int repetisjoner = res.getInt(5);
                int belastning = res.getInt(6);
                int ovelseNr = res.getInt(7);
                return new Maal(maalNr, ovelseNr, dato, tidspunkt, sett, repetisjoner, belastning);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
