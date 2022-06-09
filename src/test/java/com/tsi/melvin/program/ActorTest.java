package com.tsi.melvin.program;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActorTest {

    Actor actorMelvin = new Actor("Melvin", "Joy");
    Actor actorJohn   = new Actor("John", "Doe");
    Actor actorBob =  new Actor("Bob", "Smith");



@Test
public void test_getFirstName() {

    Assertions.assertEquals("Melvin", actorMelvin.getFirst_name(), "The expected name and actual names did not match");

}




}
