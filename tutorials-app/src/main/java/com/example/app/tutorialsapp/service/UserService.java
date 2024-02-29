package com.example.app.tutorialsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.tutorialsapp.entity.User;
import com.example.app.tutorialsapp.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User createUser(User user){

        return userRepo.save(user);
    }

    public User findUser(String name){

        return userRepo.findByName(name);
    }

    public List<User> findAllUser(){

        return (List<User>) userRepo.findAll();
    }
}
