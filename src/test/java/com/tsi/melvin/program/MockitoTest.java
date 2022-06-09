package com.tsi.melvin.program;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    private MyfirstmicroserviceApplication myfirstmicroserviceApplication;
    @Mock
    private ActorRepository actorRepository;

    @BeforeEach
    void setUp(){

        myfirstmicroserviceApplication = new MyfirstmicroserviceApplication(actorRepository);


    }

    @Test

    public void getAllActors(){
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


        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class); // mock data class
        verify(actorRepository).save(actorArgumentCaptor.capture());

    }


    @Test
    public void deleteActor(){

        Actor testActor = new Actor ("first_name", "last_name");
        myfirstmicroserviceApplication.createActor(testActor.first_name,testActor.last_name);

        testActor.setActor_id(1);
        ResponseEntity<Actor> expected = myfirstmicroserviceApplication.deleteActor(1);

        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());
        actorArgumentCaptor.getAllValues().get(0).setActor_id(1);
        verify(actorRepository).delete(actorArgumentCaptor.capture());

        Actor actual = actorArgumentCaptor.getValue();

        System.out.println(actorArgumentCaptor.getAllValues().get(0).getActor_id());


        //Assertions.assertEquals(,actual,"Delete actor method did not work properly");




    }

    @Test
    public void updateActor(){

    }





}
