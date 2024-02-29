package com.example.app.tutorialsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.app.tutorialsapp.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
    
    User findByName(String name);
}
