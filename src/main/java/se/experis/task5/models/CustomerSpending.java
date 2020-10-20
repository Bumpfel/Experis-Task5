package se.experis.task5.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerSpending extends Customer {

  private int spendings;

  public CustomerSpending(String firstName, String lastName, String country, String postalCode, String phone, int spendings) {
    super(0, firstName, lastName, country, postalCode, phone);
    this.spendings = spendings;
  }
}
