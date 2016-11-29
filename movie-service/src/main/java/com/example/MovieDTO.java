/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author milandeket
 */
public class MovieDTO {
    
    private Long movieId;
    private String name;

    public MovieDTO() {
    }
    
    public MovieDTO(Movie movie){
        this.movieId = movie.getMovieId();
        this.name = movie.getName();
    }
    
    public Long getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
