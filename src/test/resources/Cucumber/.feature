Feature: Adding a new Actor
  As a user I would like add new actor into my new table.


  Scenario: I have successfully added an actor to the table
  Given : I have the actors information
  When : I put the data into the database
    Then : I get the success return string




    Scenario: I do not have enough info
      Given : I have an actors first name
      And : I dont have their last name
      When : i try to add the data into the the database
      Then : I get an error message.
