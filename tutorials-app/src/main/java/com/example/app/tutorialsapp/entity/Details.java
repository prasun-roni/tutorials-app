package com.example.app.tutorialsapp.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "details")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on")
    private Date createdOn;

    @OneToOne(mappedBy = "details")
    @JsonBackReference
    private Tutorial tutorial;

    @Column(name = "is_published")
    private Boolean published;

    public Details() {
    }

    public Details(Date createdOn, Tutorial tutorial, Boolean published) {
        this.createdOn = createdOn;
        this.tutorial = tutorial;
        this.published = published;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Tutorial getTutorial() {
        return this.tutorial;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    public Boolean isPublished() {
        return this.published;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", tutorial='" + getTutorial() + "'" +
            ", published='" + isPublished() + "'" +
            "}";
    }

}
