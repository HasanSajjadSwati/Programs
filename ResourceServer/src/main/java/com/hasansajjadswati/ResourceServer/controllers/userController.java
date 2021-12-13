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

    @GetMapping("/product")
    public ResponseEntity<String> getProduct(){
        User user = userService.getUserByUsernameAndPassword("user","123");
        if(user.getToken().equals("asdasdqw312313213"))
            return new ResponseEntity<>("Found",HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDetails(@RequestBody User user){
        if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())){
            User newUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            userService.setToken(newUser,"asdasdqw312313213");
            HttpHeaders headers = new HttpHeaders();
            headers.set("Bearer",newUser.getToken());
            return new ResponseEntity<>(newUser.getToken(),headers, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
