package com.captechconsulting.blog.hystrixdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/demo")
    public String greeting() {
        String response;

        try {
            response = demoService.remoteCall();
            System.out.println("response: " + response);

        } catch (Exception e) {
            response = "Exception";
            e.printStackTrace();
        }
        return response + "\n";
    }
}