package com.captechconsulting.blog.hystrixdemo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Component
public class MyServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @Async
    public Future<String> callDemo() {
        String result = restTemplate.getForObject("http://localhost:8080/demo", String.class);
        return new AsyncResult<String>(result);
    }
}
