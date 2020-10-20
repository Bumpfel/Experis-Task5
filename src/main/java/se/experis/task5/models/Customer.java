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

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getSupportRepId() {
    return supportRepId;
  }

  public void setSupportRepId(Integer supportRepId) {
    this.supportRepId = supportRepId;
  }
}

