/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.List;
import org.bouncycastle.util.Store;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author milandeket
 */

public interface MovieRepo extends MongoRepository<Movie, String>{
    public Movie findByMovieId(Long movieId);
}

//@FeignClient(name = "stores", fallback = HystrixClientFallback.class)
@FeignClient(name = "stores")
interface StoreClient {
    
    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/movies/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") Long storeId, Store store);
}