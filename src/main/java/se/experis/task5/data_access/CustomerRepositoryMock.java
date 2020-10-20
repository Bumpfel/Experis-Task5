package se.experis.task5.data_access;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import se.experis.task5.models.Customer;
import se.experis.task5.models.CustomerSpending;

public class CustomerRepositoryMock {

  private ArrayList<Customer> getRandomCustomers() {
    var list = new ArrayList<Customer>();
    list.add(new Customer(2, "Mrs", "Ping", "United Kingdom", "4554", "222-1234"));
    list.add(new Customer(1, "Mr", "Crab", "USA", "81920", "555-1234"));
    list.add(new Customer(3, "Sean", "Banan", "Sweden", "387 82", "08-172992"));
    return list;
  }
  private ArrayList<CustomerSpending> getRandomCustomerSpendings() {
    var list = new ArrayList<CustomerSpending>();
    list.add(new CustomerSpending(2, "Mrs", "Ping", "United Kingdom", "4554", "222-1234", 455));
    list.add(new CustomerSpending(1, "Mr", "Crab", "USA", "81920", "555-1234", 1231));
    list.add(new CustomerSpending(3, "Sean", "Banan", "Sweden", "387 82", "08-172992", 908));
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
    var nrOfCustomersByCountry = new LinkedHashMap<String, Integer>();
    nrOfCustomersByCountry.put("Norway", 76);
    nrOfCustomersByCountry.put("Sweden", 139);
    nrOfCustomersByCountry.put("United Kingdom", 1531);
    nrOfCustomersByCountry.put("U.S.A.", 2341);
    return nrOfCustomersByCountry;
  }

  public List<CustomerSpending> getHighestSpendingCustomersOrdered() {
    return getRandomCustomerSpendings();
  }

  public List<String> getMostPopularGenre(int customerId) {
    var list = new ArrayList<String>();
    list.add("Rock");
    list.add("Indie Rock");
    return list;
  }
  
}
