package com.hasansajjadswati.ResourceServer.controllers;

import com.google.common.base.Stopwatch;
import com.hasansajjadswati.ResourceServer.entities.User;
import com.hasansajjadswati.ResourceServer.security.AES;
import com.hasansajjadswati.ResourceServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;


@RestController
public class userController {

    @Autowired
    private UserService userService;


    @GetMapping("/product/{id}")
    public ResponseEntity<String> getProduct(@RequestParam(value = "id", required = false, defaultValue = "0") String id,
                                             @RequestHeader(value="Authorization") String authHeader) throws InterruptedException {

            if(userService.findByToken(authHeader)){

                if(!userService.isExpired())
                    return new ResponseEntity<>("Product Found",HttpStatus.OK);
                else
                    return new ResponseEntity<>("Token Expired!",HttpStatus.BAD_REQUEST);
            }
            else if(!userService.findByToken(authHeader))
                return new ResponseEntity<>("Invalid Token!",HttpStatus.UNAUTHORIZED);

            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginDetails(@RequestBody User user) throws InterruptedException {

        String token = userService.randomToken(20);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization",token);

        if(userService.findByUsernameAndPassword(user.getUsername(), AES.encrypt(user.getPassword(),userService.getSecertKey()))){
            User currentUser = userService.getUserByUsernameAndPassword(user.getUsername(), AES.encrypt(user.getPassword(),userService.getSecertKey()));
            userService.setToken(currentUser,token,15);
            return ResponseEntity.ok().headers(responseHeaders).body("Login Successful!");
        }
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
