package com.example.app.tutorialsapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.tutorialsapp.entity.Details;
import com.example.app.tutorialsapp.entity.Tutorial;
import com.example.app.tutorialsapp.entity.User;
import com.example.app.tutorialsapp.service.DetailsService;
import com.example.app.tutorialsapp.service.TutorialService;
import com.example.app.tutorialsapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/tutorial")
@Tag(name = "Tutorials API")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create a Tutorial", description = "Takes a Tutorial object")
    @ResponseStatus(HttpStatus.CREATED)
    public @ApiResponse(description = "Return Turorial Object") ResponseEntity<Tutorial> createTutorial(@Parameter(description = "Tutorial Object") @RequestBody Tutorial tutorial) {

        String userName = tutorial.getUser().getName();

        if (!userService.findAllUser().isEmpty()) {
            for (User user : userService.findAllUser()) {

                if (user.getName().equals(userName)) {
                    tutorial.setUser(user);
                    break;
                }
            }
        }

        Tutorial createdTutorial = tutorialService.createTutorial(tutorial);

        if (createdTutorial != null)
            return ResponseEntity.ok(createdTutorial);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    @Operation(summary = "Retrieve all Tutorials", description = "return all tutorials at once")
    @ResponseStatus(HttpStatus.FOUND)
    public @ApiResponse(description = "Return a list of Tutorial") ResponseEntity<List<Tutorial>> getAllTutorials() {

        List<Tutorial> tutorials = tutorialService.getAllTutorials();

        if (!tutorials.isEmpty()) {
            return ResponseEntity.ok(tutorials);
        } else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a Tutorial", description = "takes an id and return the Tutorial object")
    @ResponseStatus(HttpStatus.FOUND)
    public @ApiResponse(description = "Return Tutorial Object") ResponseEntity<Tutorial> getAllTutorialById(@Parameter(description = "Tutorial Id") @PathVariable(name = "id") Long id) {

        Tutorial tutorial = tutorialService.getTutorialById(id);

        if (tutorial != null)
            return ResponseEntity.ok(tutorial);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/published")
    @Operation(summary = "Retrieve a list of Tutorials", description = "return all the published tutorials")
    @ResponseStatus(HttpStatus.FOUND)
    public @ApiResponse(description = "Return a list of Tutorials") ResponseEntity<List<Tutorial>> getPublishedTutorials() {

        List<Long> detailsId = detailsService.getPublishedDetails()
                .stream()
                .map(Details::getId)
                .collect(Collectors.toList());

        if (!detailsId.isEmpty()) {

            List<Tutorial> publishedTutorials = new ArrayList<>();

            for (Tutorial tutorial : tutorialService.getAllTutorials()) {

                if (detailsId.contains(tutorial.getDetails().getId()))
                    publishedTutorials.add(tutorial);
            }

            return ResponseEntity.ok(publishedTutorials);
        } else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    @Operation(summary = "Retrieve a list of tutorials", description = "return only published tutorials by name")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<Tutorial>> getPublishedTutorialByName(@Parameter(description = "User Name") @RequestParam(name = "name") String name,
    		@Parameter(description = "Published or Not") @RequestParam(name = "published") boolean is_published) {

        List<Tutorial> tutorials = userService.findUser(name).getTutorials();

        List<Tutorial> publishedTutorials = new ArrayList<>();

        for (Tutorial tutorial : tutorials) {

            if (tutorial.getDetails().isPublished())
                publishedTutorials.add(tutorial);
        }
    	
        if (publishedTutorials.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(publishedTutorials);
    }
}
