package database;

import treningsdagbok.Ovelse;

import java.sql.Connection;
import java.util.List;

public class TreningsoktManager
{
    private Connection connection;
    private List<Ovelse> ovelser;

    public TreningsoktManager(Connection connection)
    {
        this.connection = connection;
    }

    
}
