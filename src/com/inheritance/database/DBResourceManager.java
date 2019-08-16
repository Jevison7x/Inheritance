/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Jed Adeyemi
 */
public class DBResourceManager
{

    private Properties dbProperties;

    public DBResourceManager()
    {
        try
        {
            this.setDBProperties();
            this.createUserSystemDirectories();
        }
        catch(IOException xcp)
        {
            xcp.printStackTrace(System.err);
        }
    }

    private void setDBProperties() throws IOException
    {
        InputStream dbPropInputStream = DBResourceManager.class.getResourceAsStream("Configuration.properties");
        this.dbProperties = new Properties();
        this.dbProperties.load(dbPropInputStream);
    }

    private void createUserSystemDirectories()
    {
        String userHomeDir = System.getProperty("user.home", ".");
        //create and set the application system directory
        String app_sys_dir = this.dbProperties.getProperty("app_sys.dir");
        String appSystemDir = userHomeDir + "\\" + app_sys_dir;
        System.setProperty("app.system.INHERITANCEhome", appSystemDir);
        File appSystemFileDir = new File(appSystemDir);
        appSystemFileDir.mkdir();
        //set the application db director
        String database_dir = this.dbProperties.getProperty("database.dir");
        String databaseDir = appSystemDir + "\\" + database_dir;
        System.setProperty("app.system.INHERITANCEdatabase", databaseDir);
    }

    public String getAppSystemDirectory()
    {
        return System.getProperty("app.system.INHERITANCEhome");
    }

    public String getDatabaseDirectory()
    {
        return System.getProperty("app.system.INHERITANCEdatabase");
    }

}
