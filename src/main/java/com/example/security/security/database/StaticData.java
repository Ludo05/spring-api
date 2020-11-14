package com.example.security.security.database;

import com.example.security.security.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class StaticData {

    public List<Student> data(){
        return Arrays.asList(
          new Student.Builder().studentID(1).studentName("Lewis Williams").build(),
          new Student.Builder().studentID(2).studentName("Joe Malone").build(),
          new Student.Builder().studentID(3).studentName("Marcus Tilley").build()
        );
    }
}
