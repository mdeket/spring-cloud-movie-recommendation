/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author milandeket
 */
@FeignClient("recommendation-service")
public interface RecommendationService {
    
    @RequestMapping(method = RequestMethod.GET, value = "/recommendation/dummyData")
    void insertDummyData();
    
}
