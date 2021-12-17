package com.hasansajjadswati.ResourceServer.services;

import com.google.common.base.Stopwatch;
import com.hasansajjadswati.ResourceServer.Repository.UserRepository;
import com.hasansajjadswati.ResourceServer.entities.User;
import com.hasansajjadswati.ResourceServer.security.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private String secertKey = "springboot";
    private Stopwatch stopwatch = Stopwatch.createUnstarted();
    private long expiryTime;
    private User currentUser;

    public Boolean findByUsernameAndPassword(String username, String password){
        try {
            List<User> user = new ArrayList<>();

            if(username == null && password == null)
                return false;

            else
                userRepository.findByUsernameAndPassword(username,password).forEach(user::add);

            if(user.isEmpty())
                return false;

            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public Boolean findByToken(String token){
        try {
            List<User> user = new ArrayList<>();

            if(token == null)
                return false;

            else
                userRepository.findByToken(token).forEach(user::add);

            if(user.isEmpty())
                return false;

            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public User getUserByUsernameAndPassword(String username,String password){
        try {
            List<User> user = new ArrayList<>();

            if(username == null && password == null)
                return new User();

            else
                userRepository.findByUsernameAndPassword(username,password).forEach(user::add);

            if(user.isEmpty())
                return null;

            return user.get(0);
        }
        catch(Exception e) {
            return null;
        }
    }

    public void setToken(User user, String token,long expiryTimeInSeconds){
        user.setToken(token);
        this.expiryTime = expiryTimeInSeconds;
        currentUser = user;
        stopwatch.start();
        userRepository.save(user);
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

    public String getSecertKey(){
        return secertKey;
    }

    public boolean isExpired(){
        if(stopwatch.isRunning()){
            if(stopwatch.elapsed().toSeconds() >= expiryTime){
                stopwatch.stop();
                stopwatch.reset();
                currentUser.setToken(null);
                return true;
            }
            else
                return false;
        }
        else
            return true;
    }

    public ResponseEntity<String> loginAndToken(User user){
        String token = randomToken(20);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization",token);

        if(findByUsernameAndPassword(user.getUsername(), AES.encrypt(user.getPassword(), getSecertKey()))){
            User currentUser = getUserByUsernameAndPassword(user.getUsername(), AES.encrypt(user.getPassword(), getSecertKey()));
            setToken(currentUser,token,30);
            return ResponseEntity.ok().headers(responseHeaders).body("Login Successful!");
        }
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getProduct(String id, String authHeader){

        if(findByToken(authHeader)){

            if(!isExpired())
                return new ResponseEntity<>("Product Found",HttpStatus.OK);
            else
                return new ResponseEntity<>("Token Expired!",HttpStatus.BAD_REQUEST);
        }

        else if(!findByToken(authHeader))
            return new ResponseEntity<>("Invalid Token!",HttpStatus.UNAUTHORIZED);

        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
