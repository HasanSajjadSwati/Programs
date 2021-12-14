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

        String token = randomToken(20);

        if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())){
            User currentUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            userService.setToken(currentUser,token);
            return new ResponseEntity<>("Login Successfull! Your Auth Token = " + token,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public String randomToken(int n)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

}
