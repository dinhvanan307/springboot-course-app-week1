package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name must be at most 100 characters")
    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @NotBlank(message = "Instructor is required")
    @Size(max = 100, message = "Instructor must be at most 100 characters")
    @Column(name = "instructor", nullable = false, length = 100)
    private String instructor;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    public Course() {
    }

    public Course(Long id, String courseName, String instructor, String email) {
        this.id = id;
        this.courseName = courseName;
        this.instructor = instructor;
        this.email = email;
    }

    public Course(String courseName, String instructor, String email) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
