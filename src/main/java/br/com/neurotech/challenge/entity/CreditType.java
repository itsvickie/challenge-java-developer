package br.com.neurotech.challenge.entity;

public enum CreditType {
  FIXED_RATE, VARIABLE_RATE, CONSIGNADO, NONE;

  public static CreditType getClientCreditType(Client client) {
    int age = client.getAge();
    Double income = client.getIncome();

    if (age >= 18 && age <= 25) {
      return FIXED_RATE;
    } else if (age >= 21 && age <= 65) {
      if (income != null && income >= 5000 && income <= 15000) {
        return VARIABLE_RATE;
      }
    } else if (age > 65) {
      return CONSIGNADO;
    }

    return NONE;
  }
}
