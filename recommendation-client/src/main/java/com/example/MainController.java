/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.dto.MovieDTO;
import com.example.dto.RecommendationDTO;
import com.example.dto.UserDTO;
import com.example.service.RecommendationClientService;
import com.example.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author milandeket
 */
@RestController
@RequestMapping("/api")
public class MainController {
    
    @Autowired
    private RecommendationClientService recommendationService;
    
    @Autowired
    private UserService userService;
    
    @HystrixCommand(fallbackMethod = "recoveryRecommendation")
    @GetMapping(value ="/recommendation/user/{userId}")
    public List<MovieDTO> getRecommendation(@PathVariable(value = "userId") Long userId) throws InterruptedException{
        RecommendationDTO dto = null;
        try {
            dto = this.recommendationService.getRecommendationData(userId).get();
        } catch (ExecutionException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto.getMovies(); // if null, hystrix will call fallback method
    }
    
    public List<MovieDTO> recoveryRecommendation(Long id){
        List<MovieDTO> movies = new ArrayList();
        movies.add(new MovieDTO("10", "The Lord of the Rings: The Fellowship of the Ring"));
        movies.add(new MovieDTO("14", "One Flew Over the Cuckoo's Nest"));
        movies.add(new MovieDTO("4", "12 Angry Men"));
        movies.add(new MovieDTO("9", "Fight Club"));
        movies.add(new MovieDTO("6", "Pulp Fiction"));
        return movies;
    }
    
    @GetMapping(value = "/userDetails/{userId}")
    public UserDTO getUserDetails(@PathVariable(name = "userId") Long userId){
        return userService.getUser(userId);
    }

}
