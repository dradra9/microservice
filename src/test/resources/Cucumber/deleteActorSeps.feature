Feature: Delete an Actor from Actor Table
  As a user I would like add new actor into my new table.


  Scenario: I have successfully deleted an actor to the table
    Given : I have the actors id
    When : I pass id to database to delete
    Then : Deleted Successfuly Message
