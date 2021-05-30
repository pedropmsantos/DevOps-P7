package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    // New Scenarios - Begin
    @Given("Danny has a starting balance of {int} euros")
    public void dannyHasAStartingBalanceOfEuros(int arg0) {
    }

    @And("Danny requests a topup of {int} euros")
    public void dannyRequestsATopupOfEuros(int arg0) {
    }

    @When("The topup is processed")
    public void theTopupIsProcessed() {
    }

    @Then("The balance in his euro account should not change")
    public void theBalanceInHisEuroAccountShouldNotChange() {
    }

    @Then("The balance in his euro account should still be {int}")
    public void theBalanceInHisEuroAccountShouldStillBe(int arg0) {
    }

    @Given("Danny transfers {int} euros to another Revolut account")
    public void dannyTransfersEurosToAnotherRevolutAccount(int arg0) {
    }

    @And("Danny's initial balance is {int} euros")
    public void dannySInitialBalanceIsEuros(int arg0) {
    }

    @And("The beneficiary balance is {int} euros")
    public void theBeneficiaryBalanceIsEuros(int arg0) {
    }

    @When("Danny confirms the transaction")
    public void dannyConfirmsTheTransaction() {
    }

    @Then("Danny balance should be {int} and the beneficiary balance should be {int}")
    public void dannyBalanceShouldBeAndTheBeneficiaryBalanceShouldBe(int arg0, int arg1) {
    }
    // New Scenarios - End
}
