/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author milandeket
 */
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Collection<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        this.userRepo.findAll(0).forEach(u -> {
            users.add(u);
        });
        return users;
    }
    
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public Collection<Movie> getAllMovies(){
        List<Movie> movies = new ArrayList<>();
        this.movieRepo.findAll(0).forEach(m -> {
            movies.add(m);
        });
        return movies;
    }
    
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable(name = "userId") Long userId){
        return this.userRepo.findOne(userId, 0);
    }
    
    @RequestMapping(value = "/recommend/user/{userId}", method = RequestMethod.GET)
    public Collection<RecommendationData> getRecommendationForUser(@PathVariable(name = "userId") Long userId){
        Collection<RecommendationData> data = this.movieRepo.getRecommendationForUser(userId);
        
        // If there is no recommended movies, return top 5
        if(data == null || data.isEmpty()){
            data = this.movieRepo.getTopFiveMovies();
        }
        return data;
    }
    
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable(name = "movieId") Long movieId){
        return this.movieRepo.findOne(movieId, 0);
    }
    
    @RequestMapping(value = "/dummyData", method = RequestMethod.GET)
    public void addDummyData(){
        
        this.userRepo.deleteAll();
        this.movieRepo.deleteAll();
        
        for(int i = 0; i < 50; i++){
            this.userRepo.save(new User(new Long(i)));
        }
        
        for(int i = 0; i < 18; i++){
            this.movieRepo.save(new Movie(new Long(i)));
        }
        
        List<User> users = new ArrayList();
        this.userRepo.findAll().forEach(u -> {
            users.add(u);
        });
        
        List<Movie> movies = new ArrayList();
        this.movieRepo.findAll().forEach(m -> movies.add(m));
        
        users.stream()
                .forEach(user -> {
                    for(int i = 1; i < 10; i++){
                        Movie movie = movies.get(new Random().nextInt(18));
                        if(user.getLikes().contains(movie)){
                            continue;
                        }
                        user.getLikes().add(movie);
                        movie.getLikes().add(user);
                        userRepo.save(user);
                        movieRepo.save(movie);
                    }
                });
        
        users.stream()
                .forEach(user -> {
                    for(int i = 0; i < 25; i++){
                        User userToFollow = users.get(new Random().nextInt(50));
                        if(user.getFollowing().contains(userToFollow) 
                                || user.getId().longValue() == userToFollow.getId().longValue()){
                            continue;
                        }

                        user.getFollowing().add(userToFollow);
                        userRepo.save(user);
                    }
                });
        
        
    }
}
