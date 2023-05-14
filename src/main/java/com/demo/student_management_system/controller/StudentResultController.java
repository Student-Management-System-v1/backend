package com.demo.student_management_system.controller;

import com.demo.student_management_system.model.Course;
import com.demo.student_management_system.model.Student;
import com.demo.student_management_system.model.StudentResult;
import com.demo.student_management_system.repository.CourseRepository;
import com.demo.student_management_system.repository.StudentRepository;
import com.demo.student_management_system.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/results")
public class StudentResultController {
    @Autowired
    private StudentResultRepository studentResultRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<StudentResult> getAllResults(){
        return studentResultRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<StudentResult> saveResult(@RequestBody StudentResult result){
        Student student = studentRepository.findById(result.getStudent().getId()).get();
        Course course = courseRepository.findById(result.getCourse().getId()).get();
        if (student != null && course != null){
            result.setStudent(student);
            result.setCourse(course);
            return new ResponseEntity<>(studentResultRepository.save(result), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
