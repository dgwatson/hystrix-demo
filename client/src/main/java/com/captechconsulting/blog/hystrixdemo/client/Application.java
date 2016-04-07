package com.captechconsulting.blog.hystrixdemo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    MyServiceClient serviceClient;

    @Override
    public void run(String... args) throws Exception {

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {

            Future<String> result1 = serviceClient.callDemo();

            while (!(result1.isDone())) {
                Thread.sleep(10); //10-millisecond pause between each check
            }
            System.out.println(result1.get());
        }

        // Print results, including elapsed time
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        setDownstreamServiceLatency(7);
//
//        long start = System.currentTimeMillis();
//
//        Future<String> result1 = serviceClient.callDemo();
//        Future<String> result2 = serviceClient.callDemo();
//        Future<String> result3 = serviceClient.callDemo();
//
//        while (!(result1.isDone() && result2.isDone() && result3.isDone())) {
//            Thread.sleep(10); //10-millisecond pause between each check
//        }
//
//        // Print results, including elapsed time
//        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
//        System.out.println(result1.get());
//        System.out.println(result2.get());
//        System.out.println(result3.get());
//    }

    private void setDownstreamServiceLatency(int latencySecs) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("http://localhost:9090/downstream/responseseconds/" + latencySecs, null);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}