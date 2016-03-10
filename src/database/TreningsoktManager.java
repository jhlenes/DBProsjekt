package database;

import treningsdagbok.Ovelse;
import treningsdagbok.Treningsokt;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    public List<Ovelse> getOvelserFor(Treningsokt treningsokt)
    {
        List<Ovelse> ovelser = new ArrayList<>();
        int oktNr = treningsokt.getOktNr();
        String sql = "SELECT * FROM Treningsokt_har_ovelse WHERE oktNr = " + oktNr + ";";
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
            return ovelser;
        }
        return ovelser;
    }

    public boolean addTreningsokt(int varighet, int form, int prestasjon, String notat, int luftkvalitet, int temperatur)
    {
        Date dato = new Date(System.currentTimeMillis());
        Time tidspunkt = new Time(System.currentTimeMillis());

        String sql = "INSERT INTO Treningsokt VALUES (NULL, '" + dato + "', '" + tidspunkt + "', " + varighet + ", " + form + ", " + prestasjon +
                ", '" + notat + "', " + luftkvalitet + ", " + temperatur + ");";
        return updateSQL(sql);
    }

    public boolean deleteTreningsokt(int oktNr)
    {
        String sql = "DELETE FROM Treningsokt WHERE oktNr = " + oktNr + ";";
        return updateSQL(sql);
    }

    public List<Treningsokt> getTreningsokter()
    {
        List<Treningsokt> okter = new ArrayList<>();
        String sql = "SELECT * FROM Treningsokt;";
        try (ResultSet res = connection.createStatement().executeQuery(sql))
        {
            while (res.next())
            {
                int oktNr = res.getInt(1);
                Date dato = res.getDate(2);
                Time tidspunkt = res.getTime(3);
                int varighet = res.getInt(4);
                int form = res.getInt(5);
                int prestasjon = res.getInt(6);
                String notat = res.getString(7);
                int luftkvalitet = res.getInt(8);
                int temperatur = res.getInt(9);

                okter.add(new Treningsokt(oktNr, dato, tidspunkt, varighet, form, prestasjon, notat, luftkvalitet, temperatur));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return okter;
        }
        return okter;
    }

    public Treningsokt getLatest()
    {
        String sql = "SELECT * FROM Treningsokt ORDER BY oktNr DESC LIMIT 1;";
        try (Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                int oktNr = res.getInt(1);
                Date dato = res.getDate(2);
                Time tidspunkt = res.getTime(3);
                int varighet = res.getInt(4);
                int form = res.getInt(5);
                int prestasjon = res.getInt(6);
                String notat = res.getString(7);
                int luftkvalitet = res.getInt(8);
                int temperatur = res.getInt(9);
                return new Treningsokt(oktNr, dato, tidspunkt, varighet, form, prestasjon, notat, luftkvalitet, temperatur);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
