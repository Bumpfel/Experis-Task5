package se.experis.task5.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Comparable<Customer> {
  protected int customerId;
  protected String firstName;
  protected String lastName;
  protected String country;
  protected String postalCode;
  protected String phone;
  protected String companyName;
  protected String address;
  protected String city;
  protected String state;
  protected String fax;
  protected String email;
  protected Integer supportRepId;

  public Customer(int customerId, String firstName, String lastName, String country, String postalCode, String phone) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.postalCode = postalCode;
    this.phone = phone;
  }

  @Override
  public int compareTo(Customer other) {
    return customerId - other.customerId;
  }
}

