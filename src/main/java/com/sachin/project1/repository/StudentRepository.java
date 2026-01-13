package com.sachin.project1.repository;

// import com.sachin.project1.dto.StudentDto;
import com.sachin.project1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
  public List<StudentEntity> findByName(String name);
  public List<StudentEntity> findByEmail(String email);
}