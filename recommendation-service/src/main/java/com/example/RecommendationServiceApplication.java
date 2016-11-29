package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class RecommendationServiceApplication{
   
    public static void main(String[] args) {
            SpringApplication.run(RecommendationServiceApplication.class, args);
    }
}

@RestController
@RequestMapping("/recommendation")
class DummyDataController {
    
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
        
        for(int i = 0; i < 1000; i++){
            this.userRepo.save(new User(new Long(i)));
        }
        
        for(int i = 0; i < 18; i++){
            this.movieRepo.save(new Movie(new Long(i)));
        }
        
        List<User> users = new ArrayList();
        this.userRepo.findAll().forEach(u -> {
            u.setFollowing(new ArrayList());
            this.userRepo.save(u);
            users.add(u);
        });
        
        List<Movie> movies = new ArrayList();
        this.movieRepo.findAll().forEach(m -> movies.add(m));
        
        for(int i = 1; i < 300; i++){
            User user = users.get(new Random().nextInt(999));
            Movie movie = movies.get(new Random().nextInt(18));
            user.getLikes().add(movie);
            movie.getLikes().add(user);
            userRepo.save(user);
            movieRepo.save(movie);
        }
        
        for(int i = 0; i < 500; i++){
            User userToFollow = users.get(new Random().nextInt(999));
            User follower = users.get(new Random().nextInt(999));
            if(follower.getFollowing().contains(userToFollow)){
                continue;
            }
            
            follower.getFollowing().add(userToFollow);
            userRepo.save(follower);
        }
        
    }
    
}
