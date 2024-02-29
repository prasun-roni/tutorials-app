package com.example.app.tutorialsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.tutorialsapp.entity.Details;
import com.example.app.tutorialsapp.repository.DetailsRepo;

@Service
public class DetailsService {
    
    @Autowired
    private DetailsRepo detailsRepo;

    public Details createDetail(Details details){

        return detailsRepo.save(details);
    }

    public Details getDetailById(Long id){

        return detailsRepo.findById(id).orElse(null);
    }

    public List<Details> getPublishedDetails() {
        
        return detailsRepo.findByPublished(true);
    }
    
//    public List<Details> getPublishedByDate(Boolean isPublished, Date date){
//    	
//    	return detailsRepo.findPublishedByDate(isPublished, date);
//    }
}
