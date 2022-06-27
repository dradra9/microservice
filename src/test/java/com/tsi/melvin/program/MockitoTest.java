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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private LanguageRepository languageRepository;







    @BeforeEach
    void setUp(){


        myfirstmicroserviceApplication = new MyfirstmicroserviceApplication(actorRepository,accountRepository,filmRepository, categoryRepository, languageRepository);




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

        // see what data is being saved.
        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());

        Actor actor = actorArgumentCaptor.getValue();

        Assertions.assertEquals("first_name",actor.getFirst_name(), "First name is not the same");
        Assertions.assertEquals("last_name",actor.getLast_name(), "Last name is not the same");



    }




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

    @Test
    public void deleteFilm(){
        Film film = new Film();
        when(filmRepository.findById(1)).thenReturn(Optional.of(film));
        myfirstmicroserviceApplication.deleteFilm(1);
        verify(filmRepository).delete(film);

    }


    @Test
    public void updateFilm(){

        Film film = new Film();
        film.setFilm_id(1);
        when(filmRepository.findById(1)).thenReturn(Optional.of(film));

        myfirstmicroserviceApplication.updateFilm(1,"melvin", "joy",2,2,"w",null);
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);
        verify(filmRepository).save(filmArgumentCaptor.capture());

        Film actualFilm = filmArgumentCaptor.getValue();


        Assertions.assertEquals(1, actualFilm.getFilm_id(), "First name is not the same");
        Assertions.assertEquals("melvin",actualFilm.getTitle(), "First name is not the same");
        Assertions.assertEquals("joy",actualFilm.getDescription(), "Last name is not the same");




    }

    @Test
    public void getAllFilms(){
        myfirstmicroserviceApplication.getAllFilms();
        verify(filmRepository).findAll();
    }


    @Test
    public void getFilms(){
        Film testFilm = new Film ("title", "description",2022,20,"url", null);
        //testActor.setActor_id(0);

        myfirstmicroserviceApplication.createFilm(testFilm.title, testFilm.description,testFilm.release_year, testFilm.length,testFilm.url, testFilm.film_image);

        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class); // mock data class
        verify(filmRepository).save(filmArgumentCaptor.capture());
        Integer actualActorId = filmArgumentCaptor.getAllValues().get(0).getFilm_id();
        String actualActorFirstName = filmArgumentCaptor.getAllValues().get(0).getTitle();
        String actualActorLastName = filmArgumentCaptor.getAllValues().get(0).getDescription();

        Integer expectedId = testFilm.getFilm_id();
        String expectedFirstName = testFilm.getTitle();
        String expectedLastName = testFilm.getDescription();

        Assertions.assertEquals(expectedId,actualActorId,"Actor Id not matching");
        Assertions.assertEquals(expectedFirstName, actualActorFirstName,"Actor first name not matching");
        Assertions.assertEquals(expectedLastName, actualActorLastName, "Actor last name not matching");


    }


    @Test
    public void addFilm(){

        Film testFilm = new Film ("title", "description",2022,20,"url", null);
        myfirstmicroserviceApplication.createFilm(testFilm.title, testFilm.description,testFilm.release_year, testFilm.length,testFilm.url, testFilm.film_image);


        // see what data is being saved.
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class); // mock data class
        verify(filmRepository).save(filmArgumentCaptor.capture());

        Film film = filmArgumentCaptor.getValue();

        Integer actualActorId = filmArgumentCaptor.getAllValues().get(0).getFilm_id();
        String actualActorFirstName = filmArgumentCaptor.getAllValues().get(0).getTitle();
        String actualActorLastName = filmArgumentCaptor.getAllValues().get(0).getDescription();

        Integer expectedId = testFilm.getFilm_id();
        String expectedFirstName = testFilm.getTitle();
        String expectedLastName = testFilm.getDescription();

        Assertions.assertEquals(expectedId,actualActorId,"Actor Id not matching");
        Assertions.assertEquals(expectedFirstName, actualActorFirstName,"Actor first name not matching");
        Assertions.assertEquals(expectedLastName, actualActorLastName, "Actor last name not matching");





    }


    @Test
    public void getAllAccounts(){
      myfirstmicroserviceApplication.getAllAccounts();
      verify(accountRepository).findAll();
    }


    @Test
    public void getAccount(){
        Account testAccount = new Account ("username","password","name",1);
        //testActor.setActor_id(0);

        myfirstmicroserviceApplication.createAccount(testAccount.user_name,testAccount.password,testAccount.name,testAccount.level);

        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class); // mock data class
        verify(accountRepository).save(accountArgumentCaptor.capture());
        Integer actualAccountId = accountArgumentCaptor.getAllValues().get(0).getAccountId();
        String actualAccountUsername = accountArgumentCaptor.getAllValues().get(0).getUsername();
        String actualAccountPassword = accountArgumentCaptor.getAllValues().get(0).getPassword();

        Integer expectedId = testAccount.getAccountId();
        String expectedUsername = testAccount.getUsername();
        String expectedPassword = testAccount.getPassword();

        Assertions.assertEquals(expectedId,actualAccountId,"Actor Id not matching");
        Assertions.assertEquals(expectedUsername, actualAccountUsername,"Actor first name not matching");
        Assertions.assertEquals(expectedPassword, actualAccountPassword, "Actor last name not matching");


    }








    @Test
    void getAllCategory(){
        myfirstmicroserviceApplication.getAllFilms();
        verify(filmRepository).findAll();
    }
    @Test
    void getACategory(){
        Category testCategory = new Category(1, "testCategory");
        testCategory.setCategoryId(1);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));
        Category Actual = myfirstmicroserviceApplication.getACategory(testCategory.getCategoryId()).getBody();
        Category Expected = testCategory;
        Assertions.assertEquals(Expected,Actual,"Could not find Category with ID: ");
    }
    @Test
    void addCategory(){
        Category testCategory = new Category(1, "testCategory");
        testCategory.setCategoryId(1);
        Category Actual = myfirstmicroserviceApplication.addCategory(testCategory).getBody();
        ArgumentCaptor<Category> actorArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(actorArgumentCaptor.capture());
        Category Expected = actorArgumentCaptor.getValue();
        Assertions.assertEquals(Expected,Actual,"Category was not added.");
    }
    @Test
    void updateCategory(){
        Category testCategory = new Category(1, "testCategory");
        testCategory.setCategoryId(1);
        Category testCategoryUpdated = new Category(1, "testCategoryUpdated");
        testCategoryUpdated.setCategoryId(1);
        when(categoryRepository.findById(testCategory.getCategoryId())).thenReturn(Optional.of(testCategoryUpdated));
        Category Actual = myfirstmicroserviceApplication.updateCategory(testCategoryUpdated).getBody();
        ArgumentCaptor<Category> actorArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(actorArgumentCaptor.capture());
        Category Expected = actorArgumentCaptor.getValue();
        Assertions.assertEquals(Expected,Actual,"Category was not updated.");
    }
    @Test
    void deleteCategory(){
        Category testCategory = new Category(1, "testCategory");
        testCategory.setCategoryId(1);
        Category testCategoryDelete = new Category(1, "testCategory");
        testCategory.setCategoryId(1);
        when(categoryRepository.findById(testCategoryDelete.getCategoryId())).thenReturn(Optional.of(testCategoryDelete));
        doNothing().when(categoryRepository).deleteById(1);
        Category Actual = myfirstmicroserviceApplication.deleteCategory(testCategoryDelete).getBody();
        categoryRepository.deleteById(testCategoryDelete.getCategoryId());
        Category Expected = testCategoryDelete;
        Assertions.assertEquals(Expected,Actual,"Category was not deleted.");
    }


    @Test
    void getAllLanguage(){
        myfirstmicroserviceApplication.getAllLanguages();
        verify(languageRepository).findAll();
    }
    @Test
    void getALanguage(){
        Language testLanguage = new Language("testLanguage");
        testLanguage.setLanguageId(1);
        when(languageRepository.findById(1)).thenReturn(Optional.of(testLanguage));
        Language Actual = myfirstmicroserviceApplication.getLanguageName(testLanguage.getLanguageId()).getBody();
        Language Expected = testLanguage;
        Assertions.assertEquals(Expected,Actual,"Could not find Language with ID: ");
    }
    @Test
    void addLanguage(){
        Language testLanguage = new Language("testLanguage");
        testLanguage.setLanguageId(1);
        Language Actual = myfirstmicroserviceApplication.addLanguage(testLanguage).getBody();
        ArgumentCaptor<Language> actorArgumentCaptor = ArgumentCaptor.forClass(Language.class);
        verify(languageRepository).save(actorArgumentCaptor.capture());
        Language Expected = actorArgumentCaptor.getValue();
        Assertions.assertEquals(Expected,Actual,"Language was not added.");
    }
    @Test
    void updateLanguage(){
        Language testLanguage = new Language("testLanguage");
        testLanguage.setLanguageId(1);
        Language testLanguageUpdated = new Language("testLanguageUpdated");
        testLanguageUpdated.setLanguageId(1);
        when(languageRepository.findById(testLanguage.getLanguageId())).thenReturn(Optional.of(testLanguageUpdated));
        Language Actual = myfirstmicroserviceApplication.updateLanguage(testLanguageUpdated).getBody();
        ArgumentCaptor<Language> actorArgumentCaptor = ArgumentCaptor.forClass(Language.class);
        verify(languageRepository).save(actorArgumentCaptor.capture());
        Language Expected = actorArgumentCaptor.getValue();
        Assertions.assertEquals(Expected,Actual,"Language was not updated.");
    }
    @Test
    void deleteLanguage(){
        Language testLanguage = new Language("testLanguage");
        testLanguage.setLanguageId(1);
        Language testCategoryDelete = new Language("testLanguage");
        testCategoryDelete.setLanguageId(1);
        when(languageRepository.findById(testCategoryDelete.getLanguageId())).thenReturn(Optional.of(testCategoryDelete));
        doNothing().when(languageRepository).deleteById(1);
        Language Actual = myfirstmicroserviceApplication.deleteLanguage(testCategoryDelete).getBody();
        languageRepository.deleteById(testCategoryDelete.getLanguageId());
        Language Expected = testCategoryDelete;
        Assertions.assertEquals(Expected,Actual,"Language was not deleted.");
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
