package com.demo.student_management_system.controller;

import com.demo.student_management_system.repository.StudentRepository;
import com.demo.student_management_system.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/students")
public class StudentController {


    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentRepository.save(student),HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Not Found!"));
        studentRepository.deleteById(studentId);
    }



}
