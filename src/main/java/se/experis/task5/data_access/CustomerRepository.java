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
    private Object LinkedHashMap;

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

            ResultSet set = connection.prepareStatement("SELECT EmployeeId FROM Employee LIMIT 1").executeQuery();
            int getEmployeeId = set.getInt("EmployeeId");

            PreparedStatement prep =
                    connection.prepareStatement("INSERT INTO customer(CustomerId,FirstName,LastName,Country,PostalCode,Phone,SupportRepId)" +
                            " VALUES(25,Victor,Stevens,USA,53703,+1 (608) 257-0597");
            prep.setInt(1, customer.getCustomerId());
            prep.setString(2, customer.getFirstName());
            prep.setString(3, customer.getLastName());
            prep.setString(4, customer.getCountry());
            prep.setString(5, customer.getPostalCode());
            prep.setString(6, customer.getPhone());
            prep.setInt(7, getEmployeeId);

            int result = prep.executeUpdate();
            success = (result != 0);

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
        return success;
    }

    public Boolean updateCustomer(Customer customer) {
        Boolean success = false;
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement prep =
                    connection.prepareStatement("UPDATE customer SET CustomerId=?, FirstName=?, LastName=?, Country=?, PostalCode=?, Phone=?" +
                            " WHERE Id=?");
            prep.setInt(1, customer.getCustomerId());
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

            PreparedStatement statement = connection.prepareStatement("SELECT Country, Count (*) FROM Customer GROUP BY Country ORDER BY Count(CustomerID) DESC;");
            ResultSet result = statement.executeQuery();

            LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
            ArrayList<String> customerCountry = new ArrayList<>();
            while (result.next()) {
                String country = result.getString("country");
                int count = result.getInt("count");
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
        return (Map<String, Integer>) LinkedHashMap;
    }

    public List<CustomerSpending> getHighestSpendingCustomersOrdered() {
        ArrayList<CustomerSpending> customerSpending = new ArrayList<>();
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT customer.FirstName, customer.LastName, round(SUM(invoice.Total),2) AS total FROM Customer customer JOIN Invoice invoice ON customer.CustomerId = invoice.CustomerId GROUP BY customer.CustomerId ORDER BY total DESC;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customerSpending.add(new CustomerSpending(
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("country"),
                        result.getString("postalCode"),
                        result.getString("phone"),
                        result.getInt("spendings")));
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

    public List<String> getMostPopularGenre(int customerId) {
        ArrayList<String> popularGenre = new ArrayList<>();
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT customer.FirstName, customer.LastName, genre.Name FROM Customer customer JOIN Invoice invoice ON customer.CustomerId = invoice.CustomerId JOIN InvoiceLine invoiceLine ON invoice.CustomerId = invoiceLine.InvoiceId JOIN Track track ON invoiceLine.InvoiceId = track.TrackId JOIN Genre genre ON track.TrackId = genre.GenreId GROUP BY genre.Name ORDER BY genre.Name;");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                popularGenre.add(result.getString("Genre list"));
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