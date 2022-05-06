package com.spring.service;

import java.util.List;

import com.spring.entity.Course;
import com.spring.model.CourseDto;
import com.spring.model.ResponCourse;

public interface CourseService {
	CourseDto save(CourseDto course);

	List<ResponCourse> findAll();
	
	void deleteById(long id) throws Exception;
	
	Course findById(long id);
	
	Course upsert(Course course);
}
