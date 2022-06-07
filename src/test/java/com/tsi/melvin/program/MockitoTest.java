package com.tsi.melvin.program;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
       // Actor testActor = new Actor ("first_name", "last_name");
        //testActor.first_name
       // ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
       // verify(actorRepository).save(actorArgumentCaptor.capture());
       // actorArgumentCaptor.getValue();

    }




}
