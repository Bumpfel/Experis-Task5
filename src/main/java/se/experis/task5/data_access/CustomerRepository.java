package se.experis.task5.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import se.experis.task5.logging.Logger;
import se.experis.task5.models.Customer;
import se.experis.task5.models.CustomerSpending;

public class CustomerRepository {

    private Connection connection = null;
    private Logger logger = new Logger();

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
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                logger.error(exception.getMessage());
            }
        }
        return customers;
    }

    public Boolean addCustomer(Customer customer) {
        Boolean success = false;
        try {
            connection = DBConnectionHandler.getConnection();

            int nextCustomerId = connection.prepareStatement("SELECT CustomerId FROM Customer ORDER BY CustomerId DESC LIMIT 1")
                .executeQuery().getInt("CustomerId") + 1;
            int supportRepId = 1;connection.prepareStatement("SELECT EmployeeId FROM Employee LIMIT 1")
                .executeQuery().getInt("EmployeeId");
              
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
        } catch (Exception exception) {
                logger.error(exception.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                logger.error(exception.getMessage());
            }
        }
        return success;
    }

    public Boolean updateCustomer(Customer customer) {
        Boolean success = false;
        try {
            connection = DBConnectionHandler.getConnection();

            Integer supportRepId = customer.getSupportRepId();
            if(supportRepId == null) {   
                var stmt = connection.prepareStatement("SELECT SupportRepId FROM Customer WHERE CustomerId=?");
                stmt.setInt(1, customer.getCustomerId());
                supportRepId = stmt.executeQuery().getInt("SupportRepId");
            }

            PreparedStatement prep = connection.prepareStatement(
                "UPDATE Customer SET FirstName=?, LastName=?, Country=?, PostalCode=?, Phone=?, Company=?, Address=?, City=?, State=?, Fax=?, SupportRepId=? WHERE CustomerId=?");
            prep.setString(1, customer.getFirstName());
            prep.setString(2, customer.getLastName());
            prep.setString(3, customer.getCountry());
            prep.setString(4, customer.getPostalCode());
            prep.setString(5, customer.getPhone());
            prep.setString(6, customer.getCompanyName());
            prep.setString(7, customer.getAddress());
            prep.setString(8, customer.getCity());
            prep.setString(9, customer.getState());
            prep.setString(10, customer.getFax());
            prep.setInt(11, supportRepId);          
            prep.setInt(12, customer.getCustomerId());

            int result = prep.executeUpdate();
            success = (result != 0); // if res = 1; true
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                logger.error(exception.getMessage());
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
            logger.error(exception.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                logger.error(exception.getMessage());
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
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                logger.error(exception.getMessage());
            }
        }
        return customerSpending;
    }

    public List<String> getMostPopularGenre(int customerId) {
        ArrayList<String> popularGenre = new ArrayList<>();
        try {
            connection = DBConnectionHandler.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT Genre.Name AS genreName, Count(InvoiceLine.InvoiceId) AS count FROM Customer " +
                "JOIN Invoice ON Customer.CustomerId = Invoice.CustomerId " +
                "JOIN InvoiceLine ON Invoice.InvoiceId = InvoiceLine.InvoiceId " +
                "JOIN Track ON InvoiceLine.TrackId = Track.TrackId " +
                "JOIN Genre ON Track.GenreId = Genre.GenreId " +
                "WHERE Customer.CustomerId = ?" +
                "GROUP BY Genre.Name ORDER BY count DESC"
            );
            statement.setInt(1, customerId);
            ResultSet result = statement.executeQuery();

            int highest = -1;
            while (result.next()) {
                int count = result.getInt("count");
                if(count > highest) {
                    highest = count;
                } else if(count < highest) {
                    break;
                }                
                popularGenre.add(result.getString("genreName"));
            }
            System.out.println();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                logger.error(exception.getMessage());
            }
        }
        return popularGenre;
    }
}