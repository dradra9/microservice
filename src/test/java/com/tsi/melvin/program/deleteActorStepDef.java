package com.tsi.melvin.program;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.net.*;

import org.mockito.ArgumentCaptor;
import java.util.Optional;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

public class deleteActorStepDef {


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



    int id;
    Actor testActor;
   String actual = "";
    @Given(": I have the actors id")
    public void i_have_the_actors_id() {

        id = 1;
        testActor = new Actor("testFName", "testLName");
        actorRepository = mock(ActorRepository.class);
        myfirstmicroserviceApplication = new MyfirstmicroserviceApplication(actorRepository,accountRepository,filmRepository, categoryRepository, languageRepository);
        testActor.setActor_id(1);
        myfirstmicroserviceApplication.createActor(testActor.first_name, testActor.last_name);
        actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());

    }

    @When(": I pass id to database to delete")
    public void iPassIdToDatabaseToDelete() {

        when(actorRepository.findById(1)).thenReturn(Optional.of(testActor));
        myfirstmicroserviceApplication.deleteActor(id);
        verify(actorRepository).delete(testActor);
        actual = "Deleted sucessfully";




    }


    @Then(": Deleted Successfuly Message")
    public void deletedSuccessfulyMessage() {

        Assertions.assertEquals("Deleted sucessfully" ,actual,"Not recieved expected actor first name");

    }
}
