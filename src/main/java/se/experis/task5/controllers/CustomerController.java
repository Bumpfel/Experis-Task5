package se.experis.task5.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.experis.task5.data_access.CustomerRepository;
import se.experis.task5.models.Customer;
import se.experis.task5.models.CustomerSpending;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
  
  CustomerRepository customerRepository = new CustomerRepository();

  // used by frontend api page for prints
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
  public ResponseEntity<List<Customer>> getAllCustomers() {
    var data = customerRepository.getAllCustomers();
    if(data == null || data.isEmpty()) {
      return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(data, HttpStatus.OK);
  }
  
  @PostMapping("/customers")
  public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
    if(customerRepository.addCustomer(customer)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @PutMapping("/customers")
  public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
    if(customerRepository.updateCustomer(customer)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping("/customers/total/country")
  public ResponseEntity<Map<String, Integer>> getNrOfCustomersByCountryOrdered() {
    var data = customerRepository.getNrOfCustomersByCountryOrdered();
    if(data == null || data.isEmpty()) {
      return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(data, HttpStatus.OK);
    
  }
  
  @GetMapping("/customers/highestSpending")
  public ResponseEntity<List<CustomerSpending>> getHighestSpendingCustomersOrdered() {
    var data = customerRepository.getHighestSpendingCustomersOrdered();
    if(data == null || data.isEmpty()) {
      return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(data, HttpStatus.OK);
  }
  
  @GetMapping("/customers/{customerId}/mostPopular/genre")
  public ResponseEntity<List<String>> getMostPopularGenre(@PathVariable int customerId) {
    var data = customerRepository.getMostPopularGenre(customerId);
    if(data == null || data.isEmpty()) {
      return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(data, HttpStatus.OK);
  }
}
