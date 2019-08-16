/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.database;

//import com.TyphoidFeverDiagnosisSystem.Database.DBResourceManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author 123
 */
public class DatabaseDAO
{

    private Properties dbProperties;
    private Connection dbConnection;
    private String dbURL;
    private final String createDeceasedTableSQL = "create table APP.DECEASED_RECORDS("
            + "DECEASED_ID                  INTEGER                 NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
            + "DECEASED_NAME            VARCHAR(150)            NOT NULL, "
            + "AGE                                   INTEGER            NOT NULL, "
            + "GENDER                           VARCHAR(6)             NOT NULL, "
            + "CASE_DATE                      VARCHAR(40)             NOT NULL"
            + ")";
    private final String createAssetsTableSQL = "create table APP.ASSETS_RECORDS("
            + "SN                               INTEGER                 NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
            + "DECEASED_ID           INTEGER                 NOT NULL , "
            + "ASSET_NAME            VARCHAR(150)             NOT NULL,"
            + "ASSET_VALUE          DOUBLE             NOT NULL"
            + ")";
    private final String createHeirTableSQL = "create table APP.HEIR_RECORDS("
            + "SN                       INTEGER                 NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
            + "NAME                 VARCHAR(150)            NOT NULL, "
            + "HEIR_TYPE         VARCHAR(50)             NOT NULL,"
            + "DECEASED_ID           INTEGER                 NOT NULL "
            + ")";
    private final String createResultsTableSQL = "create table APP.RESULTS_RECORDS("
            + "SN                       INTEGER                 NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
            + "DECEASED_ID           INTEGER                 NOT NULL , "
            + "RESULTS           BLOB                NOT NULL "
            + ")";

    public DatabaseDAO()
    {
        try
        {
            this.dbProperties = this.loadDBProperties();
            String driverName = this.dbProperties.getProperty("derby.driver");
            this.loadDatabaseDriver(driverName);
            if(!this.dbExists())
                this.createDatabase();
        }
        catch(IOException xcp)
        {
            xcp.printStackTrace(System.err);
        }

    }

    public final Properties loadDBProperties() throws IOException
    {
        InputStream dbPropInputStream = DatabaseDAO.class.getResourceAsStream("Configuration.properties");
        this.dbProperties = new Properties();
        this.dbProperties.load(dbPropInputStream);
        return this.dbProperties;
    }

    private void loadDatabaseDriver(String driverName)
    {
        try
        {
            Class.forName(driverName);
        }
        catch(ClassNotFoundException xcp)
        {
            xcp.printStackTrace(System.err);
        }
    }

    private boolean dbExists()
    {
        String dbLocation = this.getDatabaseLocation();
        File dbFileDir = new File(dbLocation);
        return dbFileDir.exists();
    }

    public String getDatabaseLocation()
    {
        String dbLocation = System.getProperty("app.system.INHERITANCEdatabase");
        return dbLocation;
    }

    private boolean createDatabase() throws IOException
    {
        boolean bCreated = false;
        this.dbConnection = null;
        this.dbURL = this.getDatabaseURL();
        System.out.println("Database URL: " + this.dbURL);
        this.dbProperties.put("create", "true");
        try
        {
            this.dbConnection = DriverManager.getConnection(this.dbURL, this.dbProperties);
            bCreated = this.createInheritanceTables(dbConnection);
        }
        catch(SQLException xcp)
        {
            xcp.printStackTrace(System.err);
        }
        this.dbProperties.remove("create");
        return bCreated;
    }

    public String getDatabaseURL()
    {
        String derbyURL = this.dbProperties.getProperty("derby.url");
        String databaseDir = System.getProperty("app.system.INHERITANCEdatabase");
        this.dbURL = (derbyURL + databaseDir);
        return this.dbURL;
    }

    private boolean createInheritanceTables(Connection dbConnection) throws SQLException
    {
        Statement statement = dbConnection.createStatement();
        statement.execute(this.createDeceasedTableSQL);
        statement.execute(this.createAssetsTableSQL);
        statement.execute(this.createHeirTableSQL);
        statement.execute(this.createResultsTableSQL);
        System.out.println("Inheritance Database tables: DECEASED_RECORD, ASSETS_RECORDS, HEIR_RECORDS and RESULTS_RECORDS were successfully created!");
        return true;
    }

    public Connection getDatabaseConnection() throws SQLException
    {
        this.dbConnection = null;
        this.dbURL = this.getDatabaseURL();
        return DriverManager.getConnection(this.dbURL, this.dbProperties);
    }
}
