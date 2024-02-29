package com.example.app.tutorialsapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Tutorial> tutorials;

    @Transient
    public static Long nextId = 1L;

    public User() {
    }

    public User(String name, List<Tutorial> tutorials) {
        this.name = name;
        this.tutorials = tutorials;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tutorial> getTutorials() {
        return this.tutorials;
    }

    public void addTutorial(Tutorial tutorial) {
        this.tutorials.add(tutorial);
    }

    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials = tutorials;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", tutorials='" + getTutorials() + "'" +
                "}";
    }

}
