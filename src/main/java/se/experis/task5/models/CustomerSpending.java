package se.experis.task5.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerSpending extends Customer {

  private double spendings;

  public CustomerSpending(String firstName, String lastName, String country, String postalCode, String phone, double spendings) {
    super(0, firstName, lastName, country, postalCode, phone);
    this.spendings = spendings;
  }
}
