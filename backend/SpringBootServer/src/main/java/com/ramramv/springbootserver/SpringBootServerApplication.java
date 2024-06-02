package com.ramramv.springbootserver;

//import com.netflix.discovery.EurekaNamespace;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootServerApplication.class, args);
    }
}