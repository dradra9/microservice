package com.tsi.melvin.program;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    private MyfirstmicroserviceApplication myfirstmicroserviceApplication;
    @Mock
    private ActorRepository actorRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private FilmRepository filmRepository;




    @BeforeEach
    void setUp(){


        myfirstmicroserviceApplication = new MyfirstmicroserviceApplication(actorRepository,accountRepository,filmRepository);




    }


    public void getAllActors(){
        MockitoAnnotations.initMocks(this);
        myfirstmicroserviceApplication.getAllActors();
        verify(actorRepository).findAll();
    }



    @Test

    public void getActor(){
       Actor testActor = new Actor ("first_name", "last_name");
       //testActor.setActor_id(0);

       myfirstmicroserviceApplication.createActor(testActor.first_name,testActor.last_name);

        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class); // mock data class
        verify(actorRepository).save(actorArgumentCaptor.capture());
        String test =  actorArgumentCaptor.getAllValues().get(0).getFirst_name();
        Integer actualActorId = actorArgumentCaptor.getAllValues().get(0).getActor_id();
        String actualActorFirstName = actorArgumentCaptor.getAllValues().get(0).getFirst_name();
        String actualActorLastName = actorArgumentCaptor.getAllValues().get(0).getLast_name();


        Integer expectedId = testActor.getActor_id();
        String expectedFirstName = testActor.getFirst_name();
        String expectedLastName = testActor.getLast_name();

        //when(testActor.getActor_id()).thenReturn("first");


        //ResponseEntity<Actor> actualTest = myfirstmicroserviceApplication.getActor(0);
       //System.out.println(testActor.getActor_id());
        Assertions.assertEquals(expectedId,actualActorId,"Actor Id not matching");
        Assertions.assertEquals(expectedFirstName, actualActorFirstName,"Actor first name not matching");
        Assertions.assertEquals(expectedLastName, actualActorLastName, "Actor last name not matching");


        //System.out.println(actualActorId);
        //System.out.println(expectedId);
       // System.out.println(testActor);
        //System.out.println(actorArgumentCaptor.getAllValues().get(0));
    }

    @Test
    public void addActor(){

        Actor testActor = new Actor ("first_name", "last_name");
        myfirstmicroserviceApplication.createActor(testActor.first_name,testActor.last_name);

        // see what data is being saved.
        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());

        Actor actor = actorArgumentCaptor.getValue();

        Assertions.assertEquals("first_name",actor.getFirst_name(), "First name is not the same");
        Assertions.assertEquals("last_name",actor.getLast_name(), "Last name is not the same");



    }


    /*

    @Test
    public void deleteActor(){

        Actor testActor = new Actor ("first_name", "last_name");
        testActor.setActor_id(1);

        System.out.println(testActor.getActor_id());

        myfirstmicroserviceApplication.createActor(testActor.first_name,testActor.last_name);
        actorRepository.save(testActor);
        System.out.println(actorRepository.count());

        when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(testActor);


        //ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class); // mock data class
        //verify(this.actorRepository).save(actorArgumentCaptor.capture());




        System.out.println(actorRepository.existsById(0));
        System.out.println(actorRepository.existsById(1));
        System.out.println(actorRepository.existsById(2));



       // ResponseEntity<Actor> entityActor;
        //when(actorRepository.findById(1).get()).thenReturn(testActor);

        actorRepository.deleteById(testActor.getActor_id());

        verify(actorRepository,times(1)).delete(testActor);

        Assertions.assertNull(actorRepository.findById(1).get());




//
//        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
//        verify(actorRepository).save(actorArgumentCaptor.capture());
//        actorArgumentCaptor.getAllValues().get(0).setActor_id(1);
//        verify(actorRepository).delete(actorArgumentCaptor.capture());
//
//        Actor actual = actorArgumentCaptor.getValue();
//
//        System.out.println(actorArgumentCaptor.getAllValues().get(0).getActor_id());


        //Assertions.assertEquals(,actual,"Delete actor method did not work properly");




    }

    */

    @Test
    public void updateActor(){

        Actor actor = new Actor();
        actor.setActor_id(1);
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));

        myfirstmicroserviceApplication.updateActor(1,"melvin","joy");
        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());

         Actor actualActor = actorArgumentCaptor.getValue();


        Assertions.assertEquals(1, actualActor.getActor_id(), "First name is not the same");
        Assertions.assertEquals("melvin",actualActor.getFirst_name(), "First name is not the same");
        Assertions.assertEquals("joy",actualActor.getLast_name(), "Last name is not the same");






    }


    @Test
    public void deleteActor(){
        Actor actor = new Actor();
       when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        myfirstmicroserviceApplication.deleteActor(1);
        verify(actorRepository).delete(actor);

    }


/*
    @Test
    public void getAllAccounts(){
        MockitoAnnotations.initMocks(this);
        myfirstmicroserviceApplication.getAllAccounts();
        verify(accountRepository).findAll();
    }

*/



}
