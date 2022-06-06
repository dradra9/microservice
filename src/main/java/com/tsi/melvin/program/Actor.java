package com.tsi.melvin.program;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="actor")
public class Actor {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    // Attributes
    int actor_id;
    String first_name;
    String last_name;

    //Constructors

    public Actor(String first_name, String last_name){
        this.first_name = first_name ;
        this.last_name = last_name;
    }

    //Empty Constructor

    public Actor(){

    }

    //Methods


    public String getFirst_name() {
        return first_name;
    }

    public int getActor_id() {
        return actor_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
