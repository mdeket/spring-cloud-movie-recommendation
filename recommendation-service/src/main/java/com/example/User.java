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
public class User {
    
    @GraphId
    private Long graphId;
    
    private Long id;
    
    private String name;
    
    @Relationship(type="FOLLOWING", direction = Relationship.OUTGOING)
    List<User> following;
    
    @Relationship(type="LIKES", direction = Relationship.OUTGOING)
    List<Movie> likes;

    public User() {
        this.likes = new ArrayList();
        this.following = new ArrayList();
    }
    
    public User(Long id) {
        this.id = id;
        this.likes = new ArrayList();
        this.following = new ArrayList();
    }
    
    public User(Long id, List<Movie> likes, List<Movie> dislikes, List<User> following) {
        this.id = id;
        this.likes = likes;
        this.following = following;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Long getGraphId() {
        return graphId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public List<Movie> getLikes() {
        return likes;
    }

    public void setLikes(List<Movie> likes) {
        this.likes = likes;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

}
