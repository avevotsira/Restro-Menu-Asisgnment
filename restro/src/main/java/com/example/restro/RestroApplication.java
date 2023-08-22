package com.example.restro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestroApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestroApplication.class, args);
  }
}
