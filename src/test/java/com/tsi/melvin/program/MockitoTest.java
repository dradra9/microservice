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


       myfirstmicroserviceApplication.createActor(testActor.first_name,testActor.last_name);

        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class); // mock table
        verify(actorRepository).save(actorArgumentCaptor.capture());
        actorArgumentCaptor.getValue();
       String test =  actorArgumentCaptor.getAllValues().get(0).getFirst_name();
       Actor actualActor = actorArgumentCaptor.getAllValues().get(0);

       //ResponseEntity<Actor> actualTest = myfirstmicroserviceApplication.getActor(0);
       //System.out.println(testActor.getActor_id());
       Assertions.assertEquals(testActor,actualActor,"doesn't work");

    }

    public void addActor(){



    }





}
