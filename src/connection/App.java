package connection;

import java.sql.Connection;

public class App
{

    public static void main(String[] args)
    {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        OvelseManager ovelseManager = new OvelseManager(connection);
        ovelseManager.lagOvelse("Knebøy", "");

        dbConnection.closeConnection(connection);
    }
}
