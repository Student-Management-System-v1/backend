package com.demo.student_management_system.controller;

import com.demo.student_management_system.model.Course;
import com.demo.student_management_system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course){
        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId){
        courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found!"));
        courseRepository.deleteById(courseId);
    }
}
