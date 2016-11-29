/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 *
 * @author milandeket
 */
@FeignClient("recommendation-service")
public interface RecommendationService {
    
}
