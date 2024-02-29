package com.example.app.tutorialsapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.app.tutorialsapp.entity.Details;

@Repository
public interface DetailsRepo extends CrudRepository<Details, Long>{
    
    List<Details> findByPublished(Boolean isPublished);
    
//    @Query("SELECECT * FROM Detail WHERE published = ?1 AND createdOn = ?2")
//    List<Details> findPublishedByDate(Boolean isPublished, Date date);
}
