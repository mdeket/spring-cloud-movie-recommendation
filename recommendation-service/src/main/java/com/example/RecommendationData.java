/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.springframework.data.neo4j.annotation.QueryResult;

/**
 *
 * @author milandeket
 */
@QueryResult
public class RecommendationData {
    
    private Integer id;
    private Integer likes;

    public RecommendationData() {
    }

    public RecommendationData(Integer id, Integer likes) {
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
