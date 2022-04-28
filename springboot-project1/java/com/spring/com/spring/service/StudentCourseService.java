package com.spring.service;

import java.util.List;

import com.spring.entity.StudentCourse;
import com.spring.model.StudentCourseDto;

public interface StudentCourseService {
	StudentCourse save(StudentCourseDto dto) throws Exception;

	List<StudentCourseDto> findAll();

	StudentCourse findById(long id);

	void update(StudentCourseDto dto);
}
