/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author milandeket
 */
@Entity
public class User implements Serializable {
    
    @Id
    @GeneratedValue
    private Long Id;
    
    @Email
    private String email;
    
    private String password;
    private String name;

    public User() {
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
