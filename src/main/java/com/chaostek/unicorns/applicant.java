package com.chaostek.unicorns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import static org.apache.commons.lang3.math.NumberUtils.isParsable;
import org.apache.commons.text.StringEscapeUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class applicant
{
    private String fname, lname, email, reasons, unicornID;
    
    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    
    public applicant(String fname, String lname, String email, String reasons, String unicornID)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.reasons = reasons;
        this.unicornID = unicornID;
        
    }

    private boolean isEmailValid(String addy)
    {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(addy);
        return matcher.matches();
        
    }

    public String validate()
    {
        String ifError = "";
        //if (!NumberUtils.isParsable(unicornID)) ifError = "<p>Error in submitted unicorn ID.";
        if (fname == null) ifError = ifError + "<p>First Name can not be blank.</p>";
        else if (fname.length() > 45) ifError = ifError + "<p>The submitted first name was too long. Please limit the name to 45 characters.</p>";
        
        if (lname == null) ifError = ifError + "<p>Last Name can not be blank.</p>";
        else if (lname.length() > 45) ifError = ifError + "<p>The submited last name was too long. Please limit the name to 45 characters.</p>";
        
        if (email == null) ifError = ifError + "<p>Email can not be blank.</p>";
        else if (!isEmailValid(email)) ifError = ifError + "<p>The submitted email is not recognized as a valid email address.</p>";
        else if (email.length() > 45) ifError = ifError + "<p>The submitted email address was too long. Please limit the email to 45 characters.</p>";
        
        if (reasons == null) ifError = ifError + "<p>You have to have some reason. Please enter something.</p>";
        else if (reasons.length() > 512) ifError = ifError + "<p>The reasons you submitted contained too many characters. Please limit the reasons to 512 characters.</p>";
        return ifError;
        
    }
    
    public String submit()
    {
        String output;
        // Add code to post to database
        String ps;
        ps = "INSERT INTO references (unicornID, fname, lname, email, reasons) (?, ?, ?, ?, ?);";

        try(
            Connection conn = DriverManager.getConnection(global.DB_URL, global.USER, global.PASS);
            PreparedStatement stmt = conn.prepareStatement(ps);) // PrepStatement to reduce SQL injection risk
        {
            stmt.setInt(1, getUnicornIDInt());
            stmt.setString(2, getFnameHtml());
            stmt.setString(3, getLnameHtml());
            stmt.setString(4, getEmail());
            stmt.setString(5, getReasonsHtml());

            // Using prepStatements requires a double try-with-resources construct
            try { 
                ResultSet rtrn = stmt.executeQuery();
                output = "Success";
            }
            catch (SQLException e) {
                output = "Error saving data (" + e.getErrorCode() + "): " + e.getMessage();

            }
            finally {
                stmt.close();

            }

        }
        catch (SQLException e)
        {
            output = "Error code: " + e.getErrorCode();
        }
        
        return output;
    }

    public String getUnicornID() { return unicornID; }
    public int getUnicornIDInt() { return Integer.parseInt(unicornID); }
    public void setUnicornID(String unicornID) { this.unicornID = unicornID; }

    public String getFname() { return fname; }
    public String getFnameHtml() { return StringEscapeUtils.escapeHtml4(fname); }
    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }
    public String getLnameHtml() { return StringEscapeUtils.escapeHtml4(lname); }
    public void setLname(String lname) { this.lname = lname; }

    public String getEmail() { return email; }
    public String getEmailHtml() { return StringEscapeUtils.escapeHtml4(email); }
    public void setEmail(String email) { this.email = email; }

    public String getReasons() { return reasons; }
    public String getReasonsHtml() { return StringEscapeUtils.escapeHtml4(reasons); }
    public void setReasons(String reasons) { this.reasons = reasons; }

}
