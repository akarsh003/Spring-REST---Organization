package com.example.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

@Configuration
class SomeConfig {

  @Bean
  public SpelAwareProxyProjectionFactory projectionFactory() {
	  
    return new SpelAwareProxyProjectionFactory();
    
  }
}
