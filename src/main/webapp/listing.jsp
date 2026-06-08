<%-- 
    Document   : listing
    Created on : Sep 5, 2023, 3:29:52 PM
    Author     : chaosburn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.chaostek.unicorns.*" %>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/css/mainstyle.css" />
        <title>Ada's Awesome Adoptions | Unicorn Listing</title>
    </head>
    <body>
        <nav>
            <div class="navmenu">
                <div><a href="../index.html" class="navimage"><img src="../images/logo-xxsmall.png" /></a></div>
                <div><a href="../index.html" class="navitem">Main</a></div>
                <div><a href="/unicorns/listing.jsp" class="navactive navitem">Unicorn Listing</a></div>
                <div><a href="../mission.html" class="navitem">Mission Statement</a></div>
                <div><a href="../about.html" class="navitem">About Us</a></div>
                <div><a href="../faq.html" class="navitem">FAQ</a></div>
            </div>
        </nav>
        <main>
            <div>
                <h2>The Stable</h2>
                <p>These are the Unicorn friends we currently have for adoption</p>
            </div>
            <!-- DIVs will be generated from database info, CSS styling will make it look appropriate -->
            <div class="cardcontainer">
                <%
                    unicornsCollection ourUnicorns = new unicornsCollection();
                    try {
                        ourUnicorns.loadAll();
                        for (unicorn uni : ourUnicorns.unicorns)
                        {
                            out.println(uni.getCard());
                            
                        }

                    }
                    catch (Exception e)
                    {
                        out.println("<p>Error loading Unicorns<p>");
                    }
                %>
                    
                </div>
        </main>
        <footer>
            <p>Text by Chris and Ada Carson, 2023</p>
            <p>IT371 - Excelsior University</p>
            <p>This unicorn adoption site was created for instructional purposes only and these unicorns have already found loving homes.</p>
            <p>No unicorns were harmed in the making of this site.</p>
        </footer>
    </body>
</html>
