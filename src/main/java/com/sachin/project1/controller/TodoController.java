package com.sachin.project1.controller;
import com.sachin.project1.dto.todo;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api")
public class TodoController {
  
  private Map<Long,todo>todoList = new HashMap<>();

  @GetMapping("/todos")
  public List<todo> getTodo() {
    return new ArrayList<>(todoList.values());
  }
  
  @PostMapping("/todo")
  public String postTodo(@RequestBody todo task) {
    todoList.put(task.getId(), task);
    return "Todo Added";
  }

  @DeleteMapping("/todo/{id}")
  public todo delTodo(@PathVariable long id){
    todo taskdel = todoList.get(id);
    todoList.remove(id);
    return taskdel;
  } 

  @PutMapping("todo/{id}")
  public todo putTodo(@PathVariable Long id, @RequestBody todo entity) {
    return todoList.put(id, entity);
  }

}
