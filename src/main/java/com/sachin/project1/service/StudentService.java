package com.sachin.project1.service;
import com.sachin.project1.dto.StudentDto;

import java.util.List;

public interface StudentService {
  List<StudentDto> getAllStudents();
  StudentDto getStudentById(Long id);
  StudentDto createStudent(StudentDto dto);
  StudentDto updateStudent(Long id , StudentDto dto);
  void deleteStudent(Long id);
}
