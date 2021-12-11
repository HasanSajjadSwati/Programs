package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class Controller {


    @RequestMapping("/")
    public String greet(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getAuthorities().toString();
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> postCredentials(@RequestBody User user){
        User newUser = new User(user.getUsername(), user.getPassword());

        if(newUser.getUsername().equalsIgnoreCase("admin") && newUser.getPassword().equalsIgnoreCase("123"))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
