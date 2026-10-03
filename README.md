## __Unicorn Adoptions__

### __Name__: Chris Carson
### __Course Info__: IT371 Web Design


### __Installation and Running__:
Add a db.properties file to the resources directory that contains the database access information. Properties are:\
db.url\
db.user\
db.password\
Once the properties file is added, everything should compile cleanly using Maven and at least JDK11 into a WAR file.\
The WAR will then need to be loaded into Tomcat. This process will vary depending on your setup. The WAR now provides all of the base functionality.\
The database is MySQL and will require an active server with the database loaded.

### __Expected Outputs__:
The WAR should provide dynamic content for the unicorn adoption site.

### __Approach__:
I made a few design decisions to test the creation and feeding of dynamic content. Inparticular creating a REST style API connection for loading the "baseball cards" on the index using AJAX. I also used JSP to load and deal with the remaining dynamic content.
