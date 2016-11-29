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
    
//    @Relationship(type="FOLLOWERS", direction = Relationship.INCOMING)
//    List<User> followers;
//    
    
    @Relationship(type="FOLLOWING", direction = Relationship.OUTGOING)
    List<User> following;
    
    @Relationship(type="LIKES", direction = Relationship.OUTGOING)
    List<Movie> likes;
//
//    @Relationship(type = "DISLIKES", direction = Relationship.OUTGOING)
//    List<Movie> dislikes;

    public User() {
        this.likes = new ArrayList();
//        this.dislikes = new ArrayList();
//        this.followers = new ArrayList();
        this.following = new ArrayList();
    }
    
    public User(Long id) {
        this.id = id;
        this.likes = new ArrayList();
//        this.dislikes = new ArrayList();
//        this.followers = new ArrayList();
        this.following = new ArrayList();
    }
    
    public User(Long id, List<Movie> likes, List<Movie> dislikes, List<User> following) {
        this.id = id;
        this.likes = likes;
//        this.dislikes = dislikes;
//        this.followers = followers;
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
//
//    public List<Movie> getDislikes() {
//        return dislikes;
//    }

    public List<Movie> getLikes() {
        return likes;
    }
//
//    public void setDislikes(List<Movie> dislikes) {
//        this.dislikes = dislikes;
//    }

    public void setLikes(List<Movie> likes) {
        this.likes = likes;
    }

//    public List<User> getFollowers() {
//        return followers;
//    }

    public List<User> getFollowing() {
        return following;
    }
//
//    public void setFollowers(List<User> followers) {
//        this.followers = followers;
//    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

}
