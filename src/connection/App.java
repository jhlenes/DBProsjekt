package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App
{

    public static void main(String[] args) throws SQLException
    {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        OvelseManager ovelseManager = new OvelseManager(connection);
        ResultSet res = ovelseManager.getOvelse("Knebøy");
        res.next();

        System.out.println(res.getInt(1) + ": " + res.getString(2) + res.getString(3));

        DBConnection.closeResultSet(res);
        dbConnection.closeConnection(connection);
    }
}
