package com.chaostek.unicorns;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

public class top extends HttpServlet
{
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        
        unicornsCollection unicorns = new unicornsCollection();
        
        JSONObject jsonMap = new JSONObject();
        Map<String, String> outMap = new TreeMap<>();
        
        try
        {
            String r = unicorns.loadTop3();
            
            outMap.put("card1", unicorns.unicorns.get(0).getCard());
            outMap.put("card2", unicorns.unicorns.get(1).getCard());
            outMap.put("card3", unicorns.unicorns.get(2).getCard());
            
            response.setContentType("application/json");
            jsonMap = new JSONObject(outMap);
            String output = jsonMap.toString();
            out.print(output);
            
        }
        catch (Exception e)
        {
            jsonMap.put("error", e.getMessage());
            response.setContentType("application/json");
            out.println(jsonMap.toString());
        }
    }
}
