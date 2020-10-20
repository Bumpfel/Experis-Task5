# Experis-Task5
This application is a Rest application for handling  built in Spring with a frontend built in Thymeleaf. It uses a SQLite database.
Full task description can be found in Instructions.pdf.

The web page home lists five random artists, five random albums, and five random tracks. It also allows the user to search for songs.
![search-screenshot](https://github.com/Bumpfel/Experis-Task5/blob/develop/search-example.png?raw=true)

In addition, some data can be accessed through certain api calls (listed on the web page).
Examples of these can also be found in a postman-collection.json in the '/api-calls' folder.

# Requirements to run
* Java JDK 14+
* Spring
* Lombok

# How to run
Run main method in src/main/java/se/experis/task5/Task5Application.java.
To access the web page, visit localhost:8080. Api calls can be made to localhost:8080/api/_api-call_
