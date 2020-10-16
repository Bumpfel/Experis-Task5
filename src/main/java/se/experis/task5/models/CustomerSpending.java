package se.experis.task5.models;

public class CustomerSpending extends Customer {

  private Integer spendings;
  
  public CustomerSpending(int id, String firstName, String lastName, String country, String postalCode, String phone, int spendings) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.postalCode = postalCode;
    this.phone = phone;
    this.spendings = spendings;
  }

  public Integer getSpendings() {
    return spendings;
  }

  public void setSpendings(Integer spendings) {
    this.spendings = spendings;
  }
}
