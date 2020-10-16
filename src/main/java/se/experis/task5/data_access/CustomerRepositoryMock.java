package se.experis.task5.data_access;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import se.experis.task5.models.Customer;

public class CustomerRepositoryMock {

  private ArrayList<Customer> getRandomCustomers() {
    var list = new ArrayList<Customer>();
    list.add(new Customer(1, "Mr", "Crab", "Some Company", "West 81st St.", "New York", "New York", "USA", "81920", "555-1234", "555-9876", "mr.crab@somecompany.com", 2));
    list.add(new Customer(2, "Mrs", "Ping", "Some Other Company", "21 Downing St.", "London", "London", "United Kingdom", "4554", "222-1234", "222-9876", "mrs.ping@someothercompany.com", 2));
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
  
  public Map<Customer, Integer> getHighestSpendingCustomersOrdered() {
    var nrOfCustomersByCountry = new TreeMap<Customer, Integer>();
    var customers = getRandomCustomers();
    var random = new Random();
    for(var customer : customers) {
      nrOfCustomersByCountry.put(customer, random.nextInt(10000));
    }
    return nrOfCustomersByCountry;
  }
  
  public String getMostPopularGenre(int customerId) {
    return "Rock";
  }
  
}
