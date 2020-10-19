package se.experis.task5.data_access;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionHandler {

    public static Connection getConnection() {
        try {
            String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";

            return DriverManager.getConnection(URL);

        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
            return null;
        }

    }




    // blir nog bara osmidigt att ha en executeQuery-metod i DBHandler, man bör ju ändå preparera querien i CustomerRepository, och fånga SQLException där
    //  använd DBHandler.getConnection dock, så man inte behöver ange urlen i CustomerRepository


    // Connection getConnection()

    // boolean executeQuery(String sqlQuery)
    //tänker mig en allmän metod man använda för att exekvera vilken query som helst (returnerar huruvida querien gick igenom eller inte). borde nog inte vara public dock.

    //alt: bara ha executeQuery som den publika metoden
    //public boolean executeQuery()
    //private Connection getConnection()
    //
    //göra metoderna statiska? singleton?


}
