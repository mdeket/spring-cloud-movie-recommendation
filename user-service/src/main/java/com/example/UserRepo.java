/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author milandeket
 */
public interface UserRepo extends JpaRepository<User, Long>{
    
}
