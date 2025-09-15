package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Spring Data JPA sẽ tự động implement interface này
    // Không cần code thêm gì cho basic CRUD operations
}