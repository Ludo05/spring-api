package com.example.security.security.services;

import com.example.security.security.database.StaticData;
import com.example.security.security.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StaticData data;

    @Autowired
    public StudentService(StaticData data) {
        this.data = data;
    }

    public Student getStudent(int id) {
        return data.data().stream()
                .filter(student -> student.getStudentID() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public List<Student> getAllStudents(){
        return data.data();
    }
}
