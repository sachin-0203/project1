package com.sachin.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "students")
public class StudentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private String email;


  // Relationships

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
  @JoinColumn(name = "student_profile_id")
  private StudentProfile studentProfile;


  public Long getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getEmail(){
    return email;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setStudentProfile(StudentProfile profile){
    this.studentProfile = profile;
  }

  public StudentProfile getStudentProfile(){
    return studentProfile;
  }

}
