package com.sachin.project1.controller;

import com.sachin.project1.dto.StudentDto;
import com.sachin.project1.service.StudentService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
@RequestMapping("/students")
public class StudentController {
  
  private final StudentService studentService;

  public StudentController(StudentService studentService){
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<List<StudentDto>> getAll(){
    return ResponseEntity.ok(studentService.getAllStudents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.getStudentById(id));
  }

  @PostMapping
  public ResponseEntity<StudentDto> create(@RequestBody StudentDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto dto) {
    return ResponseEntity.ok(studentService.updateStudent(id, dto));
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    studentService.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }

  // Pagination Endpoints
  @GetMapping("/paged")
  public ResponseEntity<Page<StudentDto>> getStudentPagednSorted(
    @RequestParam(defaultValue = "0") int page, 
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy
  ) {
    return ResponseEntity.ok(
      studentService.getStudentPaginatednSorted(page,size, sortBy)
    );
  }
  

}
