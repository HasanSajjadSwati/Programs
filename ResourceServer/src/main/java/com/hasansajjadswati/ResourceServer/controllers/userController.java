package com.hasansajjadswati.ResourceServer.controllers;

import com.hasansajjadswati.ResourceServer.entities.User;
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

    private HttpHeaders headers = new HttpHeaders();
    @GetMapping("/product")
    public ResponseEntity<String> getProduct(){
        if(userService.findByToken("asdasdqw3123132133"))
            return new ResponseEntity<>("Found",HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDetails(@RequestBody User user){
        if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())){
            User newUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            userService.setToken(newUser,"asdasdqw3123132133");

            headers.set("Bearer",newUser.getToken());
            return new ResponseEntity<>(newUser.getToken(),headers, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
