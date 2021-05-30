Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 110


  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 euro in his euro Revolut account
    And Danny selects 230 euro as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 250



  #ToDo implement the remaining scenarios listed below

  #To implement this scenario you will need to use data tables
    # https://cucumber.io/docs/cucumber/api/
#  Scenario Outline: Add various amounts to Revolut account
#    Given Danny has a starting balance of <startBalance> euros
#    And Danny selects his DebitCard as his topUp method
#    When Danny now tops up by <topUpAmount>
#    Then The balance in his euro account should be <newBalance>
#    Examples:
#      | startBalance| topUpAmount | newBalance  |
#      | 0           | 100         | 100         |
#      | 14          | 20          | 34          |
#      | 23          | 30          | 53          |

#  Rule: The account balance shouldn't change if the topup payment request is rejected by the payment service

    #The scenarios below will need a payment service that accepts or rejects a request to add funds
    Scenario: Payment service rejects the request
      Given Danny has a starting balance of 50 euros
      And Danny requests a topup of 20 euros
      When The topup is processed
      Then The balance in his euro account should not change

    Scenario: Payment service accepts the request
      Given Danny has a starting balance of 50 euros
      And Danny requests a topup of 20 euros
      When The topup is processed
      Then The balance in his euro account should still be 70

    Scenario: Send money to another Revolut account
      Given Danny transfers 25 euros to another Revolut account
      And Danny's initial balance is 50 euros
      And The beneficiary balance is 100 euros
      When Danny confirms the transaction
      Then Danny balance should be 25 and the beneficiary balance should be 125
