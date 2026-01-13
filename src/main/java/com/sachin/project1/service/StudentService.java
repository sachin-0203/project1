package com.sachin.project1.service;
import com.sachin.project1.dto.StudentDto;
import com.sachin.project1.entity.StudentEntity;

import java.util.List;

import org.springframework.data.domain.Page;

public interface StudentService {
  List<StudentDto> getAllStudents();
  StudentDto getStudentById(Long id);
  StudentDto createStudent(StudentDto dto);
  StudentDto updateStudent(Long id , StudentDto dto);
  void deleteStudent(Long id);
  Page<StudentDto> getStudentPaginatednSorted(int page, int size, String sortBy);
  List<StudentDto> getStudentByName(String name);
  List<StudentDto> getStudentByEmail(String email);
}
