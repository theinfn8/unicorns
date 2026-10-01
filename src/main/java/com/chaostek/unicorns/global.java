package com.chaostek.unicorns;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class global
{
    // Change these to match 
    public static String DB_URL;
    public static String USER;
    public static String PASS;
    private static boolean loaded = false;
    
    public static void init()
    {
        if (!loaded) {
            try (InputStream input = global.class.getClassLoader().getResourceAsStream("config.properties")) {

                    Properties prop = new Properties();

                    if (input == null) {
                        System.out.println("Sorry, unable to find config.properties");
                        return;
                    }

                    //load a properties file from class path, inside static method
                    prop.load(input);

                    //get the property value and print it out
                    DB_URL = prop.getProperty("db.url");
                    USER = prop.getProperty("db.user");
                    PASS = prop.getProperty("db.password");

                    /*System.out.println(prop.getProperty("db.url"));
                    System.out.println(prop.getProperty("db.user"));
                    System.out.println(prop.getProperty("db.password")); */

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
