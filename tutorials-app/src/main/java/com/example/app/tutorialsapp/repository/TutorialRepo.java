package com.example.app.tutorialsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.app.tutorialsapp.entity.Tutorial;

@Repository
public interface TutorialRepo extends CrudRepository<Tutorial, Long>{
    

}