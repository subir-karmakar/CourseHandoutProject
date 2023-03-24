package com.subir.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.subir.jpa.model.Course;

public interface CourseRepo extends CrudRepository<Course, Integer> {

}
