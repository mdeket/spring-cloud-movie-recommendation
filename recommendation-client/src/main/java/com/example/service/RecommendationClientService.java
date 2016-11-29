/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.dto.MovieDTO;
import com.example.dto.RecommendationDTO;
import com.example.dto.RecommendedMovieDTO;
import com.example.dto.UserDTO;
import com.google.gson.Gson;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author milandeket
 */
@Service
public class RecommendationClientService {
    
    private static final Logger LOGGER = Logger.getLogger(RecommendationClientService.class.getName());
    
    @Autowired
    RestTemplate restTemplate;
    
    public CompletableFuture<UserDTO> getUserDetails(Long userId) {
        LOGGER.info("Looking for user with id: " + userId.toString());
        CompletableFuture<UserDTO> future = CompletableFuture.supplyAsync(()->{
            ParameterizedTypeReference<UserDTO> ptr =
                            new ParameterizedTypeReference<UserDTO>(){ };
                ResponseEntity<UserDTO> user = restTemplate.exchange("http://user-service/user/" + userId.toString(), HttpMethod.GET, null, ptr);
                LOGGER.info("I've found a your guy!");
                return user.getBody();
        });
        return future;
    }

    public CompletableFuture<RecommendationDTO> getRecommendationData(Long userId){
        LOGGER.info("Requesting recommendation for user with id: " + userId.toString());
        ParameterizedTypeReference<Collection<RecommendedMovieDTO>> ptrRecommendedMovie =
                        new ParameterizedTypeReference<Collection<RecommendedMovieDTO>>(){ };
        ResponseEntity<Collection<RecommendedMovieDTO>> recommendedMovies = 
                restTemplate.exchange("http://recommendation-service/recommendation/recommend/user/" + userId.toString(), HttpMethod.GET, null, ptrRecommendedMovie);
        
        LOGGER.info("Ok, lets recommend you something!");
        RecommendationDTO data = new RecommendationDTO();

        List<Integer> ids = recommendedMovies.getBody().stream().map(RecommendedMovieDTO::getId).collect(Collectors.toList());
        CompletableFuture<RecommendationDTO> test = getUserDetails(userId).thenCombine(getMovies(ids), (user, movies)->{
            data.setUser(user);
            movies.stream().forEach((recommendation) -> {
                String name =  recommendation.getName();
                Integer movieId = new Integer(recommendation.getMovieId());
                Integer likes = recommendedMovies.getBody()
                        .stream()
                        .filter(movie-> movie.getId().intValue() == movieId.intValue())
                        .findFirst()
                        .get()
                        .getLikes();
                data.getMovies().add(new MovieDTO(movieId.toString(), name, likes));
            });
            return data;
        });
        return test;
    }
    
    
    private CompletableFuture<Collection<MovieDTO>> getMovies(Collection<Integer> ids){
        LOGGER.info("Looking for movies with ids: " + ids.toString());
        Gson gson = new Gson();
        String idsString = gson.toJson(ids);
        CompletableFuture<Collection<MovieDTO>> future = CompletableFuture.supplyAsync(()->{
            ParameterizedTypeReference<Collection<MovieDTO>> ptrMovie =
                            new ParameterizedTypeReference<Collection<MovieDTO>>(){ };
                     ResponseEntity<Collection<MovieDTO>> movies = 
                        restTemplate.exchange("http://movie-service/movie/list?ids={ids}" , HttpMethod.GET, null, ptrMovie, idsString);
                LOGGER.info("I've found some movies!");
                return movies.getBody();
        });
        return future;
    };
    
    
}
