<%-- 
    Document   : apply.jsp
    Created on : Oct 1, 2023, 6:13:18 PM
    Author     : chaosburn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.chaostek.unicorns.*" %>
<%@page import="org.apache.commons.text.StringEscapeUtils" %>
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
            <%
                applicant newApplicant = new applicant(request.getParameter("fname"), request.getParameter("lname"), request.getParameter("email"), request.getParameter("reasons"), request.getParameter("uid"));
                String validation = newApplicant.validate();
                
                if (validation == "")
                {
                    newApplicant.submit();

                    %>
                    <p>Thank you, <%= newApplicant.getFnameHtml() %>, for your interest in our unicorn friends. The staff will review your information and contact you in the near future with their decision!</p>
                    <%
                }
                else
                {
                    %>
                    <p>The following error(s) were discovered in your submission: </p>
                    <% out.println(validation);
                    
                }
            %>
        </main>
        <footer>
            <p>Text by Chris and Ada Carson, 2023</p>
            <p>IT371 - Excelsior University</p>
            <p>This unicorn adoption site was created for instructional purposes only and these unicorns have already found loving homes.</p>
            <p>No unicorns were harmed in the making of this site.</p>
        </footer>
    </body>
</html>
