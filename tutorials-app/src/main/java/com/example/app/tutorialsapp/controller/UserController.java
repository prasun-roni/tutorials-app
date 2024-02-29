package com.example.app.tutorialsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.tutorialsapp.entity.Tutorial;
import com.example.app.tutorialsapp.entity.User;
import com.example.app.tutorialsapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/user")
@Tag(name = "Users API")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("")
    @Operation(summary = "Retrieve a list of tutorials by name", description = "take name as input and return all tutorials by name")
    @ResponseStatus(HttpStatus.FOUND)
    public @ApiResponse(description = "Return a list of Tutorial") ResponseEntity<List<Tutorial>> getTutorialsByName(@Parameter(description = "User Name") @RequestParam(name = "name") String name){

        User user = userService.findUser(name);

        if(user != null)
            return ResponseEntity.ok(user.getTutorials());
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Users list", description = "return all the user object")
    @ResponseStatus(HttpStatus.FOUND)
    public @ApiResponse(description = "Return a list of User") ResponseEntity<List<User>> getAllUser(){

        List<User> users = userService.findAllUser();

        if(!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return ResponseEntity.noContent().build();
    }
}
