/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author milandeket
 */
@Document
public class Movie {
    
    @Id
    private String id; 
    
    @Indexed
    private Long movieId;
    
    @Indexed
    private String name;
    
    @CreatedDate
    private Date createdDate;
    
    public Movie() {
    }
    
    public MovieDTO toDTO(){
        return new MovieDTO(this);
    }
    
    public Movie(String name, Long movieId) {
        this.name = name;
        this.createdDate = new Date();
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
}   
