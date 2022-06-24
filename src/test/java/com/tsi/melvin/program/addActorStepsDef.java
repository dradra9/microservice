package com.tsi.melvin.program;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.net.*;

import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;

public class addActorStepsDef {


    Actor testActor;
    ArgumentCaptor<Actor> actorArgumentCaptor;
    private MyfirstmicroserviceApplication myfirstmicroserviceApplication;
    @Mock
    private ActorRepository actorRepository;
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private LanguageRepository languageRepository;





    @Given(": I have the actors information")
    public void i_have_the_actors_information() {
        // Write code here that turns the phrase above into concrete actions
        testActor = new Actor ("firstName", "lastName");
        actorRepository = mock(ActorRepository.class);
        myfirstmicroserviceApplication = new MyfirstmicroserviceApplication(actorRepository,accountRepository,filmRepository, categoryRepository, languageRepository);

    }
    @When(": I put the data into the database")
    public void i_put_the_data_into_the_database() {
        myfirstmicroserviceApplication.createActor(testActor.first_name, testActor.last_name);
        actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());

    }
    @Then(": I get the success return string")
    public void i_get_the_success_return_string() {
        // Write code here that turns the phrase above into concrete actions
        String expectedFirstName = testActor.getFirst_name();
        String expectedLastName =   testActor.getLast_name();

        String actualFirstName = actorArgumentCaptor.getAllValues().get(0).getFirst_name();
        String actualLastName = actorArgumentCaptor.getAllValues().get(0).getLast_name();

        System.out.println(actorArgumentCaptor.getAllValues().get(0).getActor_id());


        //Assertions here

        Assertions.assertEquals(expectedFirstName,actualFirstName,"Not recieved expected actor first name");
        Assertions.assertEquals(expectedLastName,actualLastName,"Not recieved expected actor last name");


    }
}
