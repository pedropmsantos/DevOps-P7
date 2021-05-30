package revolut;

import java.util.Currency;
import java.util.HashMap;

public class Person {

    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        userAccounts.put("EUR", euroAccount);
    }

    public void setAccountBalance(double startingBalanace) {
        userAccounts.get("EUR").setBalance(startingBalanace);
    }

    public double getAccountBalance(String curr) {
        return userAccounts.get(curr).getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }
}
