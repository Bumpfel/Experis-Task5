package se.experis.task5.data_access;

import se.experis.task5.models.Customer;
import se.experis.task5.models.CustomerSpending;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

    private Connection connection = null;

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement prep =
                    connection.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone FROM Customer");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                customers.add(new Customer(
                        set.getInt("CustomerId"),
                        set.getString("FirstName"),
                        set.getString("LastName"),
                        set.getString("Country"),
                        set.getString("PostalCode"),
                        set.getString("Phone")));
            }
            System.out.println("It went well!");

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
            connection = DBConnectionHandler.getConnection();

            int nextCustomerId = connection.prepareStatement("SELECT CustomerId FROM Customer ORDER BY CustomerId DESC LIMIT 1")
                .executeQuery()
                .getInt("CustomerId") + 1;
            int supportRepId = 1;connection.prepareStatement("SELECT EmployeeId FROM Employee LIMIT 1")
                .executeQuery()
                .getInt("EmployeeId");

              
            PreparedStatement prep = connection.prepareStatement(
                "INSERT INTO Customer (CustomerId, FirstName, LastName, Email, Country, PostalCode, Phone, SupportRepId)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            prep.setInt(1, nextCustomerId);
            prep.setString(2, customer.getFirstName());
            prep.setString(3, customer.getLastName());
            prep.setString(4, customer.getEmail());
            prep.setString(5, customer.getCountry());
            prep.setString(6, customer.getPostalCode());
            prep.setString(7, customer.getPhone());
            prep.setInt(8, supportRepId);

            int result = prep.executeUpdate();
            success = (result != 0);

            System.out.println("Add went well!");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            // exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public Boolean updateCustomer(Customer customer) {
        Boolean success = false;
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement prep = connection.prepareStatement(
                "UPDATE Customer SET FirstName=?, LastName=?, Country=?, PostalCode=?, Phone=? WHERE CustomerId=?");
            prep.setString(1, customer.getFirstName());
            prep.setString(2, customer.getLastName());
            prep.setString(3, customer.getCountry());
            prep.setString(4, customer.getPostalCode());
            prep.setString(5, customer.getPhone());
            prep.setInt(6, customer.getCustomerId());

            System.out.println(customer.getCustomerId());
            

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

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        try {
            connection = DBConnectionHandler.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT Country, Count(CustomerId) AS total FROM Customer GROUP BY Country ORDER BY total DESC");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String country = result.getString("Country");
                int count = result.getInt("total");
                linkedHashMap.put(country, count);
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return linkedHashMap;
    }

    public List<CustomerSpending> getHighestSpendingCustomersOrdered() {
        ArrayList<CustomerSpending> customerSpending = new ArrayList<>();
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT Customer.FirstName, Customer.LastName, Customer.Country, Customer.PostalCode, Customer.Phone, round(SUM(Invoice.Total), 2) AS total FROM Customer " +
                "JOIN Invoice ON Customer.CustomerId = Invoice.CustomerId " +
                "GROUP BY Customer.CustomerId ORDER BY total DESC");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customerSpending.add(new CustomerSpending(
                    result.getString("FirstName"),
                    result.getString("LastName"),
                    result.getString("Country"),
                    result.getString("PostalCode"),
                    result.getString("Phone"),
                    result.getDouble("total"))
                );
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
        return customerSpending;
    }

    public List<String> getMostPopularGenre(int customerId) { // TODO helt fel
        ArrayList<String> popularGenre = new ArrayList<>();
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT Customer.FirstName, Customer.LastName, Genre.Name AS genreName FROM Customer " +
                "JOIN Invoice ON Customer.CustomerId = invoice.CustomerId " +
                "JOIN InvoiceLine invoiceLine ON invoice.CustomerId = invoiceLine.InvoiceId " +
                "JOIN Track track ON invoiceLine.InvoiceId = track.TrackId " +
                "JOIN Genre genre ON track.TrackId = genre.GenreId " +
                "GROUP BY genre.Name ORDER BY genre.Name");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                popularGenre.add(result.getString("genreName"));
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
        return popularGenre;
    }
}