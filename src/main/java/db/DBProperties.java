package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBProperties {
    private InputStream propertiesFile;

    private String dbName;
    private String dbHost;
    private String userName;
    private String userPsswd;

    public DBProperties (String propertiesFileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            this.propertiesFile = new FileInputStream("src/main/resources/" + propertiesFileName);

            properties.load(propertiesFile);

            this.dbName = properties.getProperty("jdbc.db");
            this.dbHost = properties.getProperty("jdbc.host");
            this.userName = properties.getProperty("jdbc.user");
            this.userPsswd = properties.getProperty("jdbc.psswd");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (propertiesFile != null) {
                try {
                    propertiesFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsswd() {
        return userPsswd;
    }

    public void setUserPsswd(String userPsswd) {
        this.userPsswd = userPsswd;
    }

}
