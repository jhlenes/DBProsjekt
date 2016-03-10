package database;

/**
 * Created by Christian on 10.03.2016.
 */

import treningsdagbok.Resultater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultatManager {
    private Connection connection;

    public ResultatManager(Connection connection)
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
}
