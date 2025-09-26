	package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name="server",configuration=RibbonConfiguration.class)
public class AmazonApplication {
	
	@Bean
	@LoadBalanced	
	public RestTemplate getRS() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AmazonApplication.class, args);
	}
}