package se.experis.task5.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.experis.task5.data_access.CustomerRepository;
import se.experis.task5.data_access.CustomerRepositoryMock;
import se.experis.task5.models.Customer;
import se.experis.task5.models.CustomerSpending;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
  
  CustomerRepository customerRepository = new CustomerRepository();

  public static List<List<String>> getAllEndpoints() {
    return List.of(
      List.of("GET", "/api/customers", "Get all customers"), 
      List.of("POST", "/api/customers", "Add a customer"),
      List.of("PUT", "/api/customers", "Update a customer"),
      List.of("GET", "/api/customers/total/country", "Get total amount of customers, grouped and ordered by country"),
      List.of("GET", "/api/customers/highestSpending", "Get customers and money spent in descending order"),
      List.of("GET", "/api/customers/:customerId/mostPopular/genre", "Get the most popular genre of a particular customer")
    );
  }

  @GetMapping("/customers")
  public List<Customer> getAllCustomers() {
    return customerRepository.getAllCustomers();
  }
  
  @PostMapping("/customers")
  public boolean addCustomer(@RequestBody Customer customer) {
    return customerRepository.addCustomer(customer);
  }

  @PutMapping("/customers")
  public boolean updateCustomer(@RequestBody Customer customer) {
    return customerRepository.updateCustomer(customer);
  }
  
  @GetMapping("/customers/total/country")
  public Map<String, Integer> getNrOfCustomersByCountryOrdered() {
    return customerRepository.getNrOfCustomersByCountryOrdered();
  }

  @GetMapping("/customers/highestSpending")
  public List<CustomerSpending> getHighestSpendingCustomersOrdered() {
    return customerRepository.getHighestSpendingCustomersOrdered();
  }

  @GetMapping("/customers/{customerId}/mostPopular/genre")
  public List<String> getMostPopularGenre(@PathVariable int customerId) {
    return customerRepository.getMostPopularGenre(customerId);
  }
}
