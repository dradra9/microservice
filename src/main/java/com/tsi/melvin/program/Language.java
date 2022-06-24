package com.tsi.melvin.program;

import javax.persistence.*;

@Entity
@Table(name = "language")
public class Language {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;
    String name;

    //Constructors
    public Language(String name){
        this.name = name;
    }
    public Language(){}

    //Methods
    public int getLanguageId() {
        return languageId;
    }
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
