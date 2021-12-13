package com.hasansajjadswati.ResourceServer.controllers;

import com.hasansajjadswati.ResourceServer.entities.User;
import com.hasansajjadswati.ResourceServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@Transactional(timeout = 5)
public class userController {

    @Autowired
    private UserService userService;



    @GetMapping("/product/{id}")
    public ResponseEntity<String> getProduct(@RequestParam(value = "id", required = false, defaultValue = "0") String id, @RequestHeader(value="Authorization") String authHeader) throws InterruptedException {
            if(userService.findByToken(authHeader))
                return new ResponseEntity<>(HttpStatus.OK);
            else if(!userService.findByToken(authHeader))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginDetails(@RequestBody User user) throws InterruptedException {
        if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())){
            User newUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            userService.setToken(newUser,"asdasdqw3123132133");
            return new ResponseEntity<>("Login Successfull! Your Auth Token = asdasdqw3123132133",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
