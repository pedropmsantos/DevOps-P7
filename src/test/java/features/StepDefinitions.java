package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Account;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    PaymentService topUpMethod;
    Person danny;
    Person dannyFriend;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        danny.setAccountBalance(startingBalance);
        double newAccountBalance = danny.getAccountBalance("EUR");
        System.out.println("Danny's new account balance of: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        System.out.println("The selected payment service type was " + topUpSource.getType());
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        topUpMethod = new PaymentService("DebitCard");
        danny.getAccount("EUR").addFunds(topUpAmount, topUpMethod);
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    // New Scenarios - Begin
    @Given("Danny has a starting balance of {double} euros")
    public void dannyHasAStartingBalanceOfEuros(double balance) {
        danny.setAccountBalance(balance);
        System.out.println("The starting balance is: " + balance);
    }

    @When("A topup of {double} euros is processed")
    public void a_topup_of_euros_is_processed(double amountToTopUp) {
        topUpMethod = new PaymentService("CreditCard");
        danny.getAccount("EUR").addFunds(amountToTopUp, topUpMethod);
    }

    @Then("The balance in his euro account should not change")
    public void theBalanceInHisEuroAccountShouldNotChange() {
        double amountToTopUp = 20;

        topUpMethod.setIsValid(false);
        danny.setAccountBalance(50);

        double currentBalance = danny.getAccountBalance("EUR");
        System.out.println("Danny's stating balance is: " + currentBalance);

        System.out.println("Processing a TopUp of: " + amountToTopUp);
        danny.getAccount("EUR").addFunds(amountToTopUp, topUpMethod);

        double newBalance = danny.getAccountBalance("EUR");
        Assert.assertEquals(currentBalance, newBalance, 0);
        System.out.println("The new balance after TopUp is: " + amountToTopUp);
    }

    @Then("The balance in his euro account should be {double}")
    public void theBalanceInHisEuroAccountShouldBe(double amountToTopUp) {
        danny.setAccountBalance(50);
        double currentBalance = danny.getAccountBalance("EUR");
        System.out.println("Danny's stating balance is: " + currentBalance);

        System.out.println("Processing a TopUp of: " + amountToTopUp);
        danny.getAccount("EUR").addFunds(amountToTopUp, topUpMethod);

        double newBalance = danny.getAccountBalance("EUR");
        Assert.assertEquals(currentBalance + amountToTopUp, newBalance, 0);
        System.out.println("The new balance after TopUp is: " + amountToTopUp);
    }

    @And("Transfer {double} euros to a {person}")
    public void transferEurosToAnotherRevolutAccount(double amountToTransfer, Person friend) {
        System.out.println("Start transfer of " + amountToTransfer);
        danny.getAccount("EUR").transferFunds(friend.getAccount("EUR"), amountToTransfer);
    }

    @And("The beneficiary balance is {double} euros")
    public void theBeneficiaryBalanceIsEuros(double balance) {
        dannyFriend = new Person("Bob");
        dannyFriend.getAccount("EUR").setBalance(balance);
    }

    @When("Danny confirms the transaction")
    public void dannyConfirmsTheTransaction() {
        danny.getAccount("EUR").transferFunds(dannyFriend.getAccount("EUR"), 100);
    }

    @Then("Danny balance should be {double}")
    public void dannyBalanceShouldBe(double balanceAfterTransfer) {
        double beneficiaryBalance = 100;
        double transferAmount = 25;
        double dannyCurrentBalance = 50;

        dannyFriend = new Person("Joe");

        System.out.println("Set initial balance for Danny: " + dannyCurrentBalance);
        danny.setAccountBalance(dannyCurrentBalance);

        System.out.println("Set beneficiary balance to: " + beneficiaryBalance);
        dannyFriend.setAccountBalance(beneficiaryBalance);

        System.out.println("Transferring: " + transferAmount);
        danny.getAccount("EUR").transferFunds(dannyFriend.getAccount("EUR"), transferAmount);

        double dannyNewBalance = danny.getAccountBalance("EUR");

        Assert.assertEquals(balanceAfterTransfer, dannyNewBalance, 0);
    }

    @And("The beneficiary balance should be {double}")
    public void theBeneficiaryBalanceShouldBe(double beneficiaryBalanceAfterTransfer) {
        double beneficiaryBalance = 100;
        double transferAmount = 25;
        double dannyCurrentBalance = 50;

        dannyFriend = new Person("Joe");

        System.out.println("Set initial balance for Danny: " + dannyCurrentBalance);
        danny.setAccountBalance(dannyCurrentBalance);

        System.out.println("Set beneficiary balance to: " + beneficiaryBalance);
        dannyFriend.setAccountBalance(beneficiaryBalance);

        System.out.println("Transferring: " + transferAmount);
        danny.getAccount("EUR").transferFunds(dannyFriend.getAccount("EUR"), transferAmount);

        double beneficiaryNewBalance = dannyFriend.getAccountBalance("EUR");

        Assert.assertEquals(beneficiaryBalanceAfterTransfer, beneficiaryNewBalance, 0);
    }

    @Then("The Transfer is not completed due to not enough funds")
    public void theTransferIsNotCompletedDueToNotEnoughFunds() {
        double beneficiaryBalance = 100;
        double transferAmount = 60;
        double dannyCurrentBalance = 50;

        dannyFriend = new Person("Joe");

        System.out.println("Set initial balance for Danny: " + dannyCurrentBalance);
        danny.setAccountBalance(dannyCurrentBalance);

        System.out.println("Set beneficiary balance to: " + beneficiaryBalance);
        dannyFriend.setAccountBalance(beneficiaryBalance);

        System.out.println("Transferring: " + transferAmount);
        danny.getAccount("EUR").transferFunds(dannyFriend.getAccount("EUR"), transferAmount);

        double dannyNewBalance = danny.getAccountBalance("EUR");
        double beneficiaryNewBalance = dannyFriend.getAccountBalance("EUR");

        Assert.assertEquals(dannyCurrentBalance, dannyNewBalance, 0);
        Assert.assertEquals(beneficiaryBalance, beneficiaryNewBalance, 0);
    }
    // New Scenarios - End
}
