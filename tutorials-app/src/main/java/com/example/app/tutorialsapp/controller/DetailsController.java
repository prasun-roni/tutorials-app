package com.example.app.tutorialsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.tutorialsapp.entity.Details;
import com.example.app.tutorialsapp.service.DetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/detail")
@Tag(name = "Details API")
public class DetailsController {
    
    @Autowired
    private DetailsService detailsService;

    @PostMapping("/create")
    @Operation(summary = "Create a Detail", description = "Takes a Details object")
    @ResponseStatus(HttpStatus.CREATED)
    public @ApiResponse(description = "Return newly created Details Object") Details createDetails(@Parameter(description = "Detail Object") @RequestBody Details details){

        return detailsService.createDetail(details);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a Detail", description = "takes an id and return the Detail object")
    @ResponseStatus(HttpStatus.FOUND)
    public @ApiResponse(description = "Return Details Object") ResponseEntity<Details> getDetailById(@Parameter(description = "Detail Id") @PathVariable("id") Long id){

        Details detail = detailsService.getDetailById(id);

        if(detail != null)
            return ResponseEntity.ok(detail);
        else
            return ResponseEntity.noContent().build();
    }
    
//    @GetMapping("/published/")
//    public ResponseEntity<List<Details>> getPublishedByDate(@RequestParam(name = "date") Date date){
//    	
//    	List<Details> details = detailsService.getPublishedByDate(true, date);
//    	
//    	if(details.isEmpty())
//    		return ResponseEntity.ok(details);
//    	else
//    		return ResponseEntity.noContent().build();
//    	
//    }
}
