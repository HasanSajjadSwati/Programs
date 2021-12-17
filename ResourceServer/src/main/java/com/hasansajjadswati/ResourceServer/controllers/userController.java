package com.hasansajjadswati.ResourceServer.controllers;

import com.hasansajjadswati.ResourceServer.entities.User;
import com.hasansajjadswati.ResourceServer.security.AES;
import com.hasansajjadswati.ResourceServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class userController {

    @Autowired
    private UserService userService;


    @GetMapping("/product/{id}")
    public ResponseEntity<String> getProduct(@RequestParam(value = "id", required = false, defaultValue = "0") String id,
                                             @RequestHeader(value="Authorization") String authHeader) throws InterruptedException {

        return userService.getProduct(id,authHeader);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginDetails(@RequestBody User user) throws InterruptedException {
        return userService.loginAndToken(user);
    }

}
