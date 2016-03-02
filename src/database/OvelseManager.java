package database;

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
            Database.closeStatement(statement);
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
            Database.closeStatement(statement);
        }
    }

    public boolean endreOvelse(String navn, String nyttNavn, String nyBeskrivelse)
    {
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Ovelse " +
                    "SET navn = '" + nyttNavn + "', beskrivelse = '" + nyBeskrivelse + "' " +
                    "WHERE navn = '" + navn + "';");
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            Database.closeStatement(statement);
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
            Database.closeStatement(statement);
        }
    }

    public boolean slettOvelse(String navn)
    {
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Ovelse WHERE navn = " + navn + ";");
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            Database.closeStatement(statement);
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
