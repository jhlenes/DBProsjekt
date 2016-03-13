package database;

import treningsdagbok.Ovelse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OvelseManager
{
    private Connection connection;

    public OvelseManager(Connection connection)
    {
        this.connection = connection;
    }

    public boolean addOvelse(String navn, String beskrivelse)
    {
        String sql = "INSERT INTO Ovelse VALUES (NULL, '" + navn + "', '" + beskrivelse + "');";
        return Database.updateSQL(sql, connection);
    }

    public boolean editOvelse(int ovelseNr, String nyttNavn, String nyBeskrivelse)
    {
        String sql = "UPDATE Ovelse " +
                "SET navn = '" + nyttNavn + "', beskrivelse = '" + nyBeskrivelse + "' " +
                "WHERE ovelseNr = " + ovelseNr + ";";
        return Database.updateSQL(sql, connection);
    }

    public boolean deleteOvelse(int ovelseNr)
    {
        String sql = "DELETE FROM Ovelse WHERE ovelseNr = " + ovelseNr + ";";
        return Database.updateSQL(sql, connection);
    }

    public List<Ovelse> getOvelser()
    {
        List<Ovelse> ovelser = new ArrayList<>();
        String sql = "SELECT * FROM Ovelse ORDER BY navn ASC;";
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
