## __Unicorn Adoptions__

### __Name__: Chris Carson
### __Course Info__: IT371 Web Design


### __Installation and Running__:
Should compile cleanly using Maven and JDK21 into a WAR file.\
The WAR will then need to be loaded into Tomcat. This provides the back-end functionality, the static parts would then need to be loaded into an Apache server that calls Tomcat as a worker.\
The database is MySQL and will require an active server with the database loaded.

### __Expected Outputs__:
The WAR should provide dynamic content for the unicorn adoption site.

### __Approach__:
I made a few design decisions to test the creation and feeding of dynamic content. Inparticular creating a REST connection for loading the "baseball cards" using AJAX. I also used JSP to load and deal with the remaining dynamic content.
