package se.experis.task5.data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import se.experis.task5.models.Customer;
import se.experis.task5.models.CustomerSpending;

public class CustomerRepositoryMock {

  private ArrayList<Customer> getRandomCustomers() {
    var list = new ArrayList<Customer>();
    list.add(new Customer(1, "Mr", "Crab", "USA", "81920", "555-1234"));
    list.add(new Customer(2, "Mrs", "Ping", "United Kingdom", "4554", "222-1234"));
    return list;
  }
  
  private ArrayList<CustomerSpending> getRandomCustomerWithSpending() {
    var list = new ArrayList<CustomerSpending>();
    list.add(new CustomerSpending(1, "Mr", "Crab", "USA", "81920", "555-1234", 849));
    list.add(new CustomerSpending(2, "Mrs", "Ping", "United Kingdom", "4554", "222-1234", 3400));
    return list;
  }


  public ArrayList<Customer> getAllCustomers() {
    return getRandomCustomers();
  }

  public boolean addCustomer(Customer customer) {
    return true;
  }
  
  public boolean updateCustomer(Customer customer) {
    return true;
  }
  
  public Map<String, Integer> getNrOfCustomersByCountryOrdered() {
    var nrOfCustomersByCountry = new TreeMap<String, Integer>();
    nrOfCustomersByCountry.put("Sweden", 120);
    nrOfCustomersByCountry.put("Norway", 76);
    nrOfCustomersByCountry.put("United Kingdom", 123);
    nrOfCustomersByCountry.put("USA", 512);
    return nrOfCustomersByCountry;
  }
  
  public ArrayList<CustomerSpending> getHighestSpendingCustomersOrdered() {
    return getRandomCustomerWithSpending();
  }
  
  public List<String> getMostPopularGenre(int customerId) {
    var list = new ArrayList<String>();
    list.add("Rock");
    list.add("Indie Rock");
    return list;
  }
  
}
