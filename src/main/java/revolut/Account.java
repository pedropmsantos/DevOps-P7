package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;

    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(double topUpAmount, PaymentService paymentService) {
        if (paymentService.getIsValid()) {
            this.balance += topUpAmount;
        } else {
            System.out.println("Payment Service is no valid : " + balance);
        }
    }

    public void transferFunds(Account beneficiaryAcc, double fundsToTransfer) {
        if (this.balance >= fundsToTransfer) {
            // Setting new balance for account transferring the funds
            this.setBalance(this.balance - fundsToTransfer);
            // Setting new balance for account receiving the funds
            beneficiaryAcc.setBalance(beneficiaryAcc.getBalance() + fundsToTransfer);
        } else {
            System.out.println("Transfer could not be completed, not enough funds to transfer " + fundsToTransfer);
        }
    }
}
