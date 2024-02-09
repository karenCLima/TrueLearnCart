package com.TrueLearn.Cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TrueLearn.Cart.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
