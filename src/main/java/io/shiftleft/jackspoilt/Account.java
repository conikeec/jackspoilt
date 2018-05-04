package io.shiftleft.jackspoilt;

import net.jcip.annotations.Immutable;

@Immutable
public class Account {

  private String customerName;
  private String accountType;
  private String accountNumber;
  private float balance;
  private Object accountNotes;

  public Account() {

  }

  public Account(String customerName, String accountType,
      String accountNumber, float balance, Object accountNotes) {
    this.customerName = customerName;
    this.accountType = accountType;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.accountNotes = accountNotes;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public Object getAccountNotes() {
    return accountNotes;
  }

  public void setAccountNotes(Object accountNotes) {
    this.accountNotes = accountNotes;
  }

  @Override
  public String toString() {
    return "Account{" +
        "customerName='" + customerName + '\'' +
        ", accountType='" + accountType + '\'' +
        ", accountNumber='" + accountNumber + '\'' +
        ", balance=" + balance +
        '}';
  }

  public Account duplicate(int id) {
    return new Account(this.customerName,
        this.accountType,
        this.accountNumber,
        this.balance,
        this.accountNotes);
  }
}
