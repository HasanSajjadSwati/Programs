package com.hasansajjadswati.requestthrottler.controller;


import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    private RateLimiter rateLimiter = RateLimiter.create(10);

    @RequestMapping("/home/{id}")
    public String sayHi(@PathVariable(value = "id", required = true) String id){
        rateLimiter.acquire(5);
        return id;
    }
}
