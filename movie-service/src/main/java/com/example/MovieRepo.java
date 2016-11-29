/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author milandeket
 */

public interface MovieRepo extends MongoRepository<Movie, String>{
    public Movie findByMovieId(Long movieId);
}