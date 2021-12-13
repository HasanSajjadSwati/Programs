package com.hasansajjadswati.ResourceServer.services;

import com.hasansajjadswati.ResourceServer.Repository.UserRepository;
import com.hasansajjadswati.ResourceServer.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

    public void setToken(User user, String token){
        user.setToken(token);
        userRepository.save(user);
    }
}
