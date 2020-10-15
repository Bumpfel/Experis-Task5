package se.experis.task5.models;

public class Customer {
  private int id;
  private String firstName;
  private String lastName;
  private String companyName;
  private String address;
  private String city;
  private String state;
  private String country;
  private String postalCode;
  private String phone;
  private String fax;
  private String email;
  private int supportRepId;

  public Customer(int id, String firstName, String lastName, String companyName, String address, String city,
      String state, String country, String postalCode, String phone, String fax, String email, int supportRepId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.address = address;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postalCode = postalCode;
    this.phone = phone;
    this.fax = fax;
    this.email = email;
    this.supportRepId = supportRepId;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getPhone() {
    return phone;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public int getSupportRepId() {
    return supportRepId;
  }

}
