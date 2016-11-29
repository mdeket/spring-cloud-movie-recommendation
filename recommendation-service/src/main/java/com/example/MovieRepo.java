/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author milandeket
 */
public interface MovieRepo extends GraphRepository<Movie>{

    @Query("MATCH (follower:User)-[f:FOLLOWING]->(u:User)-[l:LIKES]->(m:Movie) "
            + "WHERE follower.id = {0} "
            + "RETURN m.id as id, count(l) as likes "
            + "ORDER BY likes DESC "
            + "LIMIT 5")
    public List<RecommendationData> getRecommendationForUser(Long followerID);
    
    @Query("MATCH (m:Movie)<-[l:LIKES]-(u:User) "
            + "RETURN m.id as id, count(l) as likes "
            + "ORDER BY likes DESC "
            + "LIMIT 5")
    public List<RecommendationData> getTopFiveMovies();
} 
