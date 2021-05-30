package features;

import io.cucumber.java.ParameterType;
import revolut.PaymentService;
import revolut.Person;

import java.util.Currency;

public class ParameterTypes {
    @ParameterType("BankAccount|DebitCard")
    public PaymentService paymentService(String type){
        return new PaymentService(type);
    }

    @ParameterType("Friend")
    public Person person(String name){
        return new Person(name);
    }
}
