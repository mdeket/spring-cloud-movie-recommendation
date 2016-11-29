/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

/**
 *
 * @author milandeket
 */
public class MovieDTO {
    
    private String movieId;
    
    private String name;
    
    private Integer likes;

    public MovieDTO() {
    }

    public MovieDTO(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public MovieDTO(String movieId, String name, Integer likes) {
        this.movieId = movieId;
        this.name = name;
        this.likes = likes;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
