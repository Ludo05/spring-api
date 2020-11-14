package com.example.security.security.controller;

import com.example.security.security.entities.Student;
import com.example.security.security.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/management")
public class ManagementController {

    StudentService studentService;

    @Autowired
    public ManagementController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/api/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }




}
