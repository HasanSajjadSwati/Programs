package com.hasansajjadswati.SpringLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getMessage(){
        return "Hello!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginVerification(@RequestBody User user){
        return userService.loginVerification(user);
    }
}
