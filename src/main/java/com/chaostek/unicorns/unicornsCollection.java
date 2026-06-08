package com.chaostek.unicorns;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author chaosburn
 */
public class unicornsCollection
{
    public ArrayList<unicorn> unicorns;
    
    public unicornsCollection()
    {
        unicorns = new ArrayList<>();
        
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
            Connection conn = DriverManager.getConnection(global.DB_URL, global.USER, global.PASS);
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
}
