/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author milandeket
 */
@NodeEntity
public class Movie {
    
    @GraphId
    private Long graphId;
    
    private Long id;
    
    @Relationship(type="LIKES", direction = Relationship.INCOMING)
    List<User> likes;

    @Relationship(type = "DISLIKES", direction = Relationship.INCOMING)
    List<User> dislikes;

    public Movie() {
        this.likes = new ArrayList();
        this.dislikes = new ArrayList();
    }
    
    public Movie(Long id) {
        this.id = id;
        this.likes = new ArrayList();
        this.dislikes = new ArrayList();
    }

    public List<User> getLikes() {
        return likes;
    }

    public List<User> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<User> dislikes) {
        this.dislikes = dislikes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public Movie(Long id, List<User> likes, List<User> dislikes) {
        this.id = id;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }
    
}
