package com.hasansajjadswati.ResourceServer.Repository;

import com.hasansajjadswati.ResourceServer.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByToken(String token);
}
