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
public class RecommendedMovieDTO {
    
    private Integer id;
    private Integer likes;

    public RecommendedMovieDTO() {
    }

    public RecommendedMovieDTO(Integer id, Integer likes) {
        this.id = id;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
    
}
