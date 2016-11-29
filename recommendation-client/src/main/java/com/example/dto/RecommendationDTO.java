/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

import com.example.dto.UserDTO;
import com.example.dto.MovieDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author milandeket
 */
public class RecommendationDTO {
    
    private UserDTO user;
    private List<MovieDTO> movies;

    public RecommendationDTO() {
        this.movies = new ArrayList();
    }

    public RecommendationDTO(UserDTO user, List<MovieDTO> movies) {
        this.user = user;
        this.movies = movies;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
