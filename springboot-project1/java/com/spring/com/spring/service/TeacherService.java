package com.spring.service;

import java.text.ParseException;
import java.util.List;

import com.spring.entity.Teacher;
import com.spring.model.ResponTeacher;
import com.spring.model.TeacherDto;

public interface TeacherService {
	List<ResponTeacher> lstTeacherDto();
	
	TeacherDto save(TeacherDto teacher) throws ParseException;
	
	void deleteById(long id) throws Exception;
	
	TeacherDto upsert(Teacher teacher);
	
	Teacher findById(long id);
}
