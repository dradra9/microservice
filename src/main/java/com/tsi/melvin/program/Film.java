package com.tsi.melvin.program;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;

import java.sql.Blob;


import javax.persistence.*;

@Entity
@Table(name="film")
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int film_id;
    String title;
    String description;
    int release_year;
    int language_id = 1;
    int length;
    String url;
    @JsonSerialize(using = SqlBlobSerializer.class) Blob film_image;

    public Film(String title, String description, int release_year, int length,String url, Blob film_image){
        title = this.title;
        description = this.description;
        release_year = this.release_year;
        length = this.length;
        url = this.url;
        film_image = this.film_image;


    }

    public Film(){

    }

    public Blob getFilm_image() {
        return film_image;
    }

    public int getFilm_id() {
        return film_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public int getLength() {
        return length;
    }

    public int getRelease_year() {
        return release_year;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilm_image(Blob film_image) {
        this.film_image = film_image;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

