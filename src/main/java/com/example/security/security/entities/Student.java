package com.example.security.security.entities;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Student {

    private final int studentID;
    private final String studentName;

    private Student(Builder builder) {
        this.studentID = builder.studentID;
        this.studentName = builder.studentName;

    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studentID == student.studentID &&
                Objects.equals(studentName, student.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, studentName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + this.studentID +
                ", studentName='" + this.studentName + '\'' +
                '}';
    }

    public static class Builder {

        private int studentID;
        private String studentName;

        public Builder studentID(int studentID) {
            this.studentID = studentID;
            return this;
        }

        public Builder studentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        // Returns the build student object which was created using the builder static class
        public Student build() {
            return new Student(this);
        }
    }
}
