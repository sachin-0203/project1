package com.sachin.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor    //auto created ALL CONSTRUCTOR 
public class StudentDto {
  private long id;
  private String name;
  private String email;

}
