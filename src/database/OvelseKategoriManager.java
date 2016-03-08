package database;

/**
 * Created by Christian on 08.03.2016.
 */

import treningsdagbok.OvelseKategori;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OvelseKategoriManager {

    private Connection connection;

    public OvelseKategoriManager(Connection connection)
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

    public boolean addOvelseKategori (String navn, String beskrivelse){
        String sql = "INSERT INTO Kategori VALUES('" + navn + "', '" + beskrivelse + "');";
        return updateSQL(sql);
    }

    
    // Bare for å teste
    /*
    public static void main(String[] args) {
        Database database = new Database();
        Connection connection = database.getConnection();
        OvelseKategoriManager okm = new OvelseKategoriManager(connection);
        okm.addOvelseKategori("Bein","Bein");
    }
    */
}
