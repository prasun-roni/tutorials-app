package com.example.app.tutorialsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.tutorialsapp.entity.Tutorial;
import com.example.app.tutorialsapp.entity.User;
import com.example.app.tutorialsapp.repository.TutorialRepo;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepo tutorialRepo;

    public Tutorial createTutorial(Tutorial tutorial) {

        Long id = tutorial.getUser().getId();

        if (id == null) {
            tutorial.getUser().setId(User.nextId++);
        }
        return tutorialRepo.save(tutorial);
    }

    public List<Tutorial> getAllTutorials() {
        return (List<Tutorial>) tutorialRepo.findAll();
    }

    public Tutorial getTutorialById(Long id) {
        return tutorialRepo.findById(id).orElse(null);
    }
}
