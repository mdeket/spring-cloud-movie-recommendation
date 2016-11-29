/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author milandeket
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.example")
@EnableTransactionManagement
public class MyConfiguration extends Neo4jConfiguration {
    
    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
       org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
       config
           .driverConfiguration()
           .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
           .setURI("http://neo4j:root@localhost:7474");
       return config;
    }

    
    @Bean
    @Override
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(getConfiguration(), "com.example");
    }

    // needed for session in view in web-applications
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Override
    public Session getSession() throws Exception {
        return super.getSession();
    }

}