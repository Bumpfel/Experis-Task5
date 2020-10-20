# Experis-Task5
This application is a Rest application for handling  built in Spring with a frontend built in Thymeleaf. It uses a SQLite database.
Full task description can be found in Instructions.pdf.

The web page home lists five random artists, five random albums, and five random tracks. It also allows the user to search for songs.
![search-screenshot](/search-example.png)

In addition, some data can be accessed through certain api calls (listed on the web page).
Examples of these can also be found in a [postman collection file](/api-calls/api-calls.postman_collection.json), along with the body needed for add customer and update customer.

# Requirements to run
* Java JDK 14+
* Spring
* Lombok

# How to run
Run main method in src/main/java/se/experis/task5/Task5Application.java.
To access the web page, visit localhost:8080. Api calls can be made to localhost:8080/api/\[api-call\]
