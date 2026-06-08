<%-- 
    Document   : unicorn
    Created on : Sep 9, 2023, 9:07:32 AM
    Author     : chaosburn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.chaostek.unicorns.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/css/mainstyle.css" />
        <link rel="stylesheet" href="/css/over.css" />
        <script src="/js/overlay.js"></script>
        <title>Ada's Awesome Adoptions | Unicorn</title>
    </head>
    <body>
        <nav>
            <div class="navmenu">
                <div><a href="../index.html" class="navimage"><img src="../images/logo-xxsmall.png" /></a></div>
                <div><a href="../index.html" class="navitem">Main</a></div>
                <div><a href="/unicorns/listing.jsp" class="navitem">Unicorn Listing</a></div>
                <div><a href="../mission.html" class="navitem">Mission Statement</a></div>
                <div><a href="../about.html" class="navitem">About Us</a></div>
                <div><a href="../faq.html" class="navitem">FAQ</a></div>
            </div>
        </nav>
        <main>
            <div id="overlay">
                <div class="close" onclick="unloadForm()">&times;</div>
                <form action="apply.jsp" method="POST">
                    <input type="hidden" name="uid" value="<%= request.getParameter("id") %>"/>
                    <label for="fname">First Name:</label><input type="text" name="fname" maxlength="45"></input><br />
                    <label for="lname">Last Name:</label><input type="text" name="lname" maxlength="45"></input><br />
                    <label for="email">Email:</label><input type="email" name="email" maxlength="45"></input><br />
                    <label for="reasons">Why do you want to adopt this unicorn?</label><br />
                    <textarea rows="5" cols="50" name="reasons" maxlength="512"></textarea><br />
                    <input type="submit" />
                </form>
            </div>
            <% 
                unicornsCollection ourUnicorns = new unicornsCollection();
                try {
                    String r = ourUnicorns.loadSingle(Integer.parseInt(request.getParameter("id")));
                    unicorn myUni = ourUnicorns.unicorns.get(0);
            %>
            <h3 class="bioname"><%= myUni.getName() %></h3>
            <div class="biosection">
                
                <img src='../images/<%= myUni.getUnicornID() %>/<%= myUni.getImagePath() %>' alt='A Unicorn' class='biophoto'/>
                <div clas='biotext'>
                    <button onclick='loadForm(<%= myUni.getUnicornID() %>)'>Adopt me!</button><br />
                    <span class='bioheading'>Name: </span><span><%= myUni.getName() %></span><br />
                    <span class='bioheading'>Gender: </span><span><%= myUni.getGender() %></span><br />
                    <span class='bioheading'>Description: </span><span><%= myUni.getDescription() %></span><br />
                    <span class='bioheading'>Personality: </span><span><%= myUni.getPersonality() %></span><br />
                    <span class='bioheading'>Likes: </span><br />
                    <%
                        // Use normal for because we need the count
                        for(int i = 0; i < myUni.getInterests().size(); i++)
                        {
                        %>
                    <span class='bioheading'><%= i+1 %>: </span><span><%= myUni.getInterests().get(i) %></span><br />
                    <%
                        }
                    %>
                </div>
                    <%
                }
                catch (Exception e)
                {
                    out.println(e.getMessage());
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
