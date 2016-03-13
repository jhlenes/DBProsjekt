package database;

import treningsdagbok.Maal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaalManager
{
    private Connection connection;

    public MaalManager(Connection connection)
    {
        this.connection = connection;
    }

    public boolean addMaal(Date dato, Time tidspunkt, int sett, int repetisjoner, int belastning, int ovelseNr)
    {
        String sql = "INSERT INTO Maal VALUES (NULL, '" + dato.toString() + "', '" + tidspunkt.toString() + "', "
                + sett + ", " + repetisjoner + ", " + belastning + ", " + ovelseNr + ");";
        return Database.updateSQL(sql, connection);
    }

    public boolean deleteMaal(int maalNr)
    {
        String sql = "DELETE FROM Maal WHERE maalNr = " + maalNr + ";";
        return Database.updateSQL(sql, connection);
    }

    public List<Maal> getAlleMaalFor(int ovelseNr)
    {
        List<Maal> maal = new ArrayList<>();
        String sql = "SELECT * FROM Maal WHERE ovelseNr = " + ovelseNr + " ORDER BY dato DESC, tidspunkt DESC;";
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
                maal.add(new Maal(maalNr, dato, tidspunkt, sett, repetisjoner, belastning, ovelseNr));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return maal;
    }

}
