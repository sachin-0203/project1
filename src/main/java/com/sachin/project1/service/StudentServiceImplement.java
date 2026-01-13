package com.sachin.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.sachin.project1.dto.StudentDto;
import com.sachin.project1.entity.StudentEntity;
import com.sachin.project1.entity.StudentProfile;
import com.sachin.project1.exception.StudentNotFoundException;
import com.sachin.project1.repository.StudentRepository;


import java.util.*;


@Service
public class StudentServiceImplement implements StudentService {
  
  @Autowired
  private StudentRepository studentRepository;

  public StudentServiceImplement(StudentRepository studentRepository){
    this.studentRepository = studentRepository;
  }

  @Override
  public List<StudentDto> getAllStudents(){
    List<StudentEntity> entities = studentRepository.findAll();
    return entities.stream().map(this::maptoDto).toList();
  }

  @Override
  public StudentDto getStudentById(Long id){
    StudentEntity entity = studentRepository.findById(id)
    .orElseThrow(()-> new StudentNotFoundException("Student not found with id: " + id));
    return maptoDto(entity);
  }

  @Override
  public StudentDto createStudent(StudentDto dto){
    StudentEntity entity = mapToEntity(dto);
    StudentEntity saved = studentRepository.save(entity);
    return maptoDto(saved);
  }

  @Override
  public StudentDto updateStudent(Long id , StudentDto dto){
    StudentEntity student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student not found with id: " + id));
    student.setName(dto.getName());
    student.setEmail(dto.getEmail());

    if(student.getStudentProfile() == null){
      StudentProfile profile = new StudentProfile();
      profile.setDob(dto.getDob());
      student.setStudentProfile(profile);
    } 
    else{
      student.getStudentProfile().setDob(dto.getDob());
    }

    StudentEntity updatedStudent = studentRepository.save(student);
    return maptoDto(updatedStudent);
  }

  @Override
  public void deleteStudent(Long id){
    if(!studentRepository.existsById(id)){
      throw new StudentNotFoundException("Student not found with id: " + id);
    }
    studentRepository.deleteById(id);
  }


  // map methods

  private StudentEntity mapToEntity(StudentDto dto){
    StudentEntity entity = new StudentEntity();
    entity.setName(dto.getName());
    entity.setEmail(dto.getEmail());
    
    if(dto.getDob() != null ){
      StudentProfile profile = new StudentProfile();
      profile.setDob(dto.getDob());
      entity.setStudentProfile(profile);
    }
    
    return entity;
  }


  private StudentDto maptoDto(StudentEntity entity){
    
    StudentDto dto = new StudentDto();
    dto.setName(entity.getName());
    dto.setEmail(entity.getEmail());

    if(entity.getStudentProfile() != null){
      dto.setDob(entity.getStudentProfile().getDob());
    }

    return dto;
  }


  // Pagination Logic
  @Override
  public Page<StudentDto> getStudentPaginatednSorted(int page, int size, String sortBy){
    Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).ascending());
    Page<StudentEntity> entities = studentRepository.findAll(pageable);
    return entities.map(this::maptoDto);
  }

  // Custome query methods
  @Override
  public List<StudentDto> getStudentByName(String name){
    List<StudentEntity> students =  studentRepository.findByName(name);
    if(students.isEmpty()){
      throw new StudentNotFoundException("No Student is found with name: " + name);
    }
    return students.stream().map(this::maptoDto).toList();
  }

  @Override
  public List<StudentDto> getStudentByEmail(String email){
    List<StudentEntity> students =  studentRepository.findByEmail(email);
    if(students.isEmpty()){
      throw new StudentNotFoundException("No Students found with email: " + email);
    }
    return  students.stream().map(this::maptoDto).toList();
  }


}
