package io.shiftleft.jackspoilt;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountStore {

  private Map<String, Account> accounts = new HashMap<>();
  private AtomicInteger idGenerator = new AtomicInteger(0);

  public AccountStore() {
    add(new Account("mary", "checking", getAlphaNumeric(10), 1000, createNotesData()));
    add(new Account("mary", "savings", getAlphaNumeric(10), 6000, createNotesData()));
    add(new Account("bob", "mma", getAlphaNumeric(10), 10000, createNotesData()));
    add(new Account("bob", "savings", getAlphaNumeric(10), 7500, createNotesData()));
    add(new Account("steve", "checking", getAlphaNumeric(10), 10000, createNotesData()));
    add(new Account("steve", "savings", getAlphaNumeric(10), 100000, createNotesData()));
  }

  public Collection<Account> list() {
    return Collections.unmodifiableCollection(accounts.values());
  }

  public Account findById(String id) {
    return accounts.get(id);
  }

  public Account add(Account newAccount) {
    Integer newId = idGenerator.incrementAndGet();
    Account product = newAccount.duplicate(newId);
    accounts.put(newId.toString(), product);
    return product;
  }

  public Account update(String id, Account newAccount) {
    Account oldAccount = accounts.get(id);
    if (oldAccount == null) {
      return null;
    }
    accounts.put(id, newAccount);
    return newAccount;
  }

  public Account delete(String id) {
    return accounts.remove(id);
  }

  private Object createNotesData() {
    Map<String, Object> data = new HashMap<>();
    data.put("randomnotes", getAlphaNumeric(20));
    return data;
  }


  public String getAlphaNumeric(int len) {

    char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    char[] c = new char[len];
    SecureRandom random = new SecureRandom();
    for (int i = 0; i < len; i++) {
      c[i] = ch[random.nextInt(ch.length)];
    }

    return new String(c);
  }


}
