package com.chaostek.unicorns;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class unicornsCollection
{
    String DB_URL;
    String USER;
    String PASS;
    
    public ArrayList<unicorn> unicorns;
    
    public unicornsCollection()
    {
        unicorns = new ArrayList<>();
        try (InputStream input = global.class.getClassLoader().getResourceAsStream("db.properties")) {

                Properties prop = new Properties();

                if (input == null) {
                    System.out.println("Sorry, unable to load database properties");

                }
                else {
                    //load a properties file from class path, inside static method
                    prop.load(input);

                    //get the property value and print it out
                    DB_URL = prop.getProperty("db.url");
                    USER = prop.getProperty("db.user");
                    PASS = prop.getProperty("db.password");

                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String loadSingle(int uid)
    {
        return loadUnicorns("SELECT * FROM unicorns WHERE unicornID = " + Integer.toString(uid) + " ORDER BY dateentered DESC");
        
    }
    
    public String loadAll()
    {
        return loadUnicorns("SELECT * FROM unicorns ORDER BY dateentered DESC");
        
    }
    
    public String loadTop3()
    {
        return loadUnicorns("SELECT * FROM unicorns ORDER BY dateentered DESC LIMIT 3;");
        
    }
    
    public String loadUnicorns(String unicornSQL)
    {
        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(unicornSQL);)
        {
            if (rs!=null)
            {
                while(rs.next())
                {
                    unicorn tempUnicorn = new unicorn(rs.getInt("unicornID"), rs.getString("name"), rs.getString("gender"), rs.getString("description"), rs.getString("personality"), rs.getString("imagepath"));
                    
                    String interestsSQL;
                    interestsSQL = "SELECT * FROM interests WHERE unicornID = " + tempUnicorn.getUnicornID();
                    
                    try(
                        Statement interestsStmt = conn.createStatement();
                        ResultSet interestsRS = interestsStmt.executeQuery(interestsSQL);)
                    {
                        if (interestsRS != null)
                        {
                            while(interestsRS.next())
                            {
                                tempUnicorn.addInterest(interestsRS.getString("interestText"));

                            }
                        }
                    }
                    unicorns.add(tempUnicorn);
                    
                }

            }
            else
            {
                return "Failed to load recordset from database";
            }
            
        } catch (SQLException e)
        {
            
            return e.getMessage();
            //System.out.println(e.getStackTrace());
        }
        
        return "Loaded " + unicorns.size() + " unicorns";
        
    }
    
    public int count(){
        return unicorns.size();
    }
    
    public String getDB() { return DB_URL;}
    public String getUser() { return USER;}
    public String getPass() { return PASS; }
}
