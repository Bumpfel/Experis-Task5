package se.experis.task5.data_access;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerRepository {


    // private static Connection getConnection();
    //public static boolean exeCuteQuery(String sqlQuery) {

    private String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private Connection connection = null;

    /*
     Setup methods to manipulate database, using conn = DriverManager.getConnection(URL);
     and prepared statements.
    */

    //CRUD

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        // ---
        try {
            // connect
            connection = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    connection.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone FROM Customer");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                customers.add(new Customer(
                        set.getString("CustomerId"),
                        set.getString("FirstName"),
                        set.getString("LastName"),
                        set.getString("Country"),
                        set.getString("PostalCode"),
                        set.getString("Phone"),
                        ));
            }
            System.out.println("Get all went well!");

        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }

        return customers;
    }

    public Boolean addCustomer(Customer customer) {
        Boolean success = false;
        try {

            connection = DriverManager.getConnection(URL);

            ResultSet set = connection.prepareStatement("SELECT EmployeeId FROM Employee LIMIT 1").executeQuery();
            int getEmployeeId = set.getInt("EmployeeId");

            PreparedStatement prep =
                    connection.prepareStatement("INSERT INTO customer(CustomerId,FirstName,LastName,Country,PostalCode,Phone,SupportRepId)" +
                            " VALUES(25,Victor,Stevens,USA,53703,+1 (608) 257-0597");
            prep.setString(1, customer.getCustomerId());
            prep.setString(2, customer.getFirstName());
            prep.setString(3, customer.getLastName());
            prep.setString(4, customer.getCountry());
            prep.setString(5, customer.getPostalCode());
            prep.setString(6, customer.getPhone());
            prep.setString(7, getEmployeeId);

            int result = prep.executeUpdate();
            success = (result != 0); // if res = 1; true

            System.out.println("Add went well!");

        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        // ---
        return success;
    }

    public Boolean updateCustomer(Customer customer) {
        Boolean success = false;
        try {
            // connect
            connection = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    connection.prepareStatement("UPDATE customer SET CustomerId=?, FirstName=?, LastName=?, Country=?, PostalCode=?, Phone=?" +
                            " WHERE Id=?");
            prep.setString(1, customer.getCustomerId());
            prep.setString(2, customer.getFirstName());
            prep.setString(3, customer.getLastName());
            prep.setString(4, customer.getCountry());
            prep.setString(5, customer.getPostalCode());
            prep.setString(6, customer.getPhone());

            int result = prep.executeUpdate();
            success = (result != 0); // if res = 1; true

            System.out.println("Update went well!");

        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }

        return success;
    }

    public Map<String, Integer> getNrOfCustomersByCountryOrdered() {

    try {
        connection = DBConnectionHandler.getConnection();

        ResultSet result = connection;

        connection.prepareStatement("SELECT Country, Count (*) FROM Customer GROUP BY Country (CustomerID) ORDER BY Country DESC").executeQuery();

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

            linkedHashMap.put("", 1);
            linkedHashMap.put();
            linkedHashMap.put();
            for(Map.Entry<String,Integer> entry : linkedHashMap.entrySet())
            {
                System.out.println(entry.getKey()+" "+entry.getValue());
            }
        }

             catch (Exception exception) {
                 System.out.println(exception.toString());
             }

        }

    // den bibehåller insättningsordningen, så sorteringen kan ske i sql-querien



    public Map<Customer, Integer> getHighestSpendingCustomersOrdered() {


    }


    public ArrayList<String> getMostPopularGenre(int customerId) {


    }



/*
    public Customer getSpecificCustomer(String id){
        Customer customer = null;
        // ---
        try{
            // connect
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Id, CompanyName, ContactName, Phone " +
                            "FROM customer WHERE Id=?");
            prep.setString(1,id);
            ResultSet set = prep.executeQuery();
            while(set.next()){
                customer = new Customer(
                        set.getString("Id"),
                        set.getString("CompanyName"),
                        set.getString("ContactName"),
                        set.getString("Phone")
                );
            }
            System.out.println("Get specific went well!");

        }catch(Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            } catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        // ---

        return customer;
    }
     */