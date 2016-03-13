package database;

import treningsdagbok.Ovelse;
import treningsdagbok.Treningsokt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreningsoktManager
{
    private Connection connection;

    public TreningsoktManager(Connection connection)
    {
        this.connection = connection;
    }

    public List<Ovelse> getOvelserFor(Treningsokt treningsokt)
    {
        List<Ovelse> ovelser = new ArrayList<>();
        int oktNr = treningsokt.getOktNr();
        String sql = "SELECT Ovelse.ovelseNr, Ovelse.navn, Ovelse.beskrivelse FROM Treningsokt_har_ovelse INNER JOIN Ovelse" +
                " ON Treningsokt_har_ovelse.ovelseNr = Ovelse.ovelseNr  WHERE oktNr = " + oktNr + ";";
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
        return Database.updateSQL(sql, connection);
    }

    public boolean deleteTreningsokt(int oktNr)
    {
        String sql = "DELETE FROM Treningsokt WHERE oktNr = " + oktNr + ";";
        return Database.updateSQL(sql, connection);
    }

    public List<Treningsokt> getTreningsokter()
    {
        List<Treningsokt> okter = new ArrayList<>();
        String sql = "SELECT * FROM Treningsokt ORDER BY dato DESC, tidspunkt DESC;";
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

    public List<String> getTreningsNotater()
    {
        List<String> notater = new ArrayList<>();
        String sql = "SELECT dato, notat FROM Treningsokt ORDER BY dato DESC, tidspunkt DESC;";
        try (ResultSet res = connection.createStatement().executeQuery(sql))
        {
            while (res.next())
            {
                Date dato = res.getDate(1);
                String notat = res.getString(2);

                notater.add(dato + ": " + notat);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return notater;
        }
        return notater;
    }

    public void addOvelserTo(int oktNr, List<Ovelse> ovelser)
    {
        for (Ovelse ovelse : ovelser)
        {
            int ovelseNr = ovelse.getOvelseNr();
            String sql = "INSERT INTO Treningsokt_har_ovelse VALUES ( " + oktNr + ", " + ovelseNr + ");";
            Database.updateSQL(sql, connection);
        }
    }

    public void addOvelserToLatest(List<Ovelse> ovelser)
    {
        String sql = "SELECT MAX(oktNr) FROM Treningsokt;";
        try (ResultSet res = connection.createStatement().executeQuery(sql))
        {
            while (res.next())
            {
                int oktNr = res.getInt(1);

                for (Ovelse ovelse : ovelser)
                {
                    int ovelseNr = ovelse.getOvelseNr();
                    String sql2 = "INSERT INTO Treningsokt_har_ovelse VALUES ( " + oktNr + ", " + ovelseNr + ");";
                    Database.updateSQL(sql2, connection);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
