package connection;

import java.sql.Connection;
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
            return false;
        } finally
        {
            DBConnection.closeStatement(statement);
        }
    }



}
