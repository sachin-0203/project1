package com.sachin.project1.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_profiles")
public class StudentProfile{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  public void setDob(LocalDate dob){
    this.dob = dob;
  }

  public LocalDate getDob(){
    return dob;
  }
}