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

}
