package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseManager
{

    private Connection connection;

    public OvelseManager(Connection connection)
    {
        this.connection = connection;
    }

    public boolean lagOvelse(String navn, String beskrivelse)
    {
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Ovelse VALUES (NULL, '" + navn + "', '" + beskrivelse + "');");
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            DBConnection.closeStatement(statement);
        }
    }

    public boolean endreOvelse(int ovelseNr, String nyttNavn, String nyBeskrivelse)
    {
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Ovelse " +
                    "SET navn = '" + nyttNavn + "', beskrivelse = '" + nyBeskrivelse + "' " +
                    "WHERE ovelseNr = " + ovelseNr + ";");
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            DBConnection.closeStatement(statement);
        }
    }

    public boolean slettOvelse(int ovelseNr)
    {
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Ovelse WHERE ovelseNr = " + ovelseNr + ";");
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            DBConnection.closeStatement(statement);
        }
    }

    public ResultSet getOvelse(String navn)
    {
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM Ovelse WHERE navn = '" + navn + "';");
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

}
