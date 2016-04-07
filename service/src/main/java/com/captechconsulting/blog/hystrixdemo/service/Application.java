package com.captechconsulting.blog.hystrixdemo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DemoService demoService() {
        return new HystrixDemoService();
    }

    @Bean
    public RestTemplate restTemplate() {
        //HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        //clientHttpRequestFactory.setReadTimeout(10000);
        //clientHttpRequestFactory.setConnectTimeout(10000);
        //return new RestTemplate(clientHttpRequestFactory);
        return new RestTemplate();
    }
}
