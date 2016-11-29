package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients(basePackages = "com.example")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class RecommendationClientApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(RecommendationClientApplication.class, args);
	}
}
