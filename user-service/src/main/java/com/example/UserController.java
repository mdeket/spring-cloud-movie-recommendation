/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author milandeket
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserRepo customerRepo;
    
    @RequestMapping(method = RequestMethod.GET)
    public Collection getAllUsers(){
        return this.customerRepo.findAll();
    }
    
    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public User getUsers(@PathVariable(name = "userId") Long userId){
        return this.customerRepo.findOne(userId);
    }
    
    
    
}
