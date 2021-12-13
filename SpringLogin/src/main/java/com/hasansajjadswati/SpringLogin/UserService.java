package com.hasansajjadswati.SpringLogin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public ResponseEntity<String> loginVerification(User user){
        try{
            if(user.getUsername().equals("user") && user.getPassword().equals("123"))
                return new ResponseEntity<>("\"access_token\":\"MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3\",\n" +
                        "  \"token_type\":\"bearer\",\n" +
                        "  \"expires_in\":180,\n" +
                        "  \"scope\":\"create\"",HttpStatus.OK);

            else if (user.getUsername().isEmpty() || user.getPassword().isEmpty())
                return new ResponseEntity<>("error\": \"invalid_request\",\n" +
                        "\"error_description\": \"Request was missing the 'password' parameter.\",",HttpStatus.BAD_REQUEST);

            else
                return new ResponseEntity<>("error\": \"invalid_request\",\n" +
                        "\"error_description\": \"Invalid Password Or Username.\",",HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
