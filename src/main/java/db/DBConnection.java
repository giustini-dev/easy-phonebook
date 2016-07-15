package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String PROPERTIES_FILE_NAME = "db.properties";
    private static final DBConnection uniqueInstance = new DBConnection();

    Connection connection;
    Statement query;

    private DBConnection () {
        DBProperties dbProperties = new DBProperties(PROPERTIES_FILE_NAME);

        String db = dbProperties.getDbName();
        String host = dbProperties.getDbHost();
        String user = dbProperties.getUserName();
        String psswd = dbProperties.getUserPsswd();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                    host + "/" + db + "?user=" +
                    user + "&password=" + psswd);
            System.out.println("Conexión a la base de datos '" + db + "' establecida.");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = null;
    }

    public static DBConnection getInstance () {
        return uniqueInstance;
    }

    public boolean isConnected () {
        try {
            return connection.isValid(0);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

