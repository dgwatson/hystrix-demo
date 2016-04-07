package com.captechconsulting.blog.hystrixdemo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HystrixDemoService implements DemoService {

    private static final String URI = "http://localhost:9090/downstream";

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackHandler")
    public String remoteCall() throws Exception {
        try {

            RequestEntity<Void> requestEntity = RequestEntity.get(new URI(URI)).accept(MediaType.APPLICATION_JSON).build();
            ResponseEntity<String> rawResponse = restTemplate.exchange(requestEntity, String.class);

            return rawResponse.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String fallbackHandler() {
        return "!!!! FALLBACK !!!!";
    }
}
