/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import java.util.Collection;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author milandeket
 */
@FeignClient("user-service")
public interface MovieService {
    
    @RequestMapping(method = RequestMethod.GET, value = "/movie")
    Collection getMovies();
    
}
