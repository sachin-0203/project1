package com.sachin.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.sachin.project1.dto.StudentDto;
import com.sachin.project1.entity.StudentEntity;
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
    return entity;
  }

  private StudentDto maptoDto(StudentEntity entity){
    return new StudentDto(entity.getId(), entity.getName(), entity.getEmail());
  }


  // Pagination Logic
  public Page<StudentDto> getStudentPaginated(int page, int size){
    Pageable pageable = PageRequest.of(page,size);
    Page<StudentEntity> entities = studentRepository.findAll(pageable);
    return entities.map(this::maptoDto);
  }

}
