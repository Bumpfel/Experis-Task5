package se.experis.task5.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerSpending extends Customer {

  private int spendings;

  public CustomerSpending(int id, String firstName, String lastName, String country, String postalCode, String phone, int spendings) {
    super(id, firstName, lastName, country, postalCode, phone);
    this.spendings = spendings;
  }
}
