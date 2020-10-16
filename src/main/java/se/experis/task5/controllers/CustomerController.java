package se.experis.task5.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.experis.task5.data_access.CustomerRepositoryMock;
import se.experis.task5.models.Customer;
import se.experis.task5.models.CustomerSpending;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
  
  CustomerRepositoryMock customerRepository = new CustomerRepositoryMock();

  @GetMapping("/customers")
  public ArrayList<Customer> getAllCustomers() {
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
  public ArrayList<CustomerSpending> getHighestSpendingCustomersOrdered() {
    return customerRepository.getHighestSpendingCustomersOrdered();
  }

  @GetMapping("/customers/{customerId}/mostPopular/genre")
  public List<String> getMostPopularGenre(@PathVariable int customerId) {
    return customerRepository.getMostPopularGenre(customerId);
  }
}
