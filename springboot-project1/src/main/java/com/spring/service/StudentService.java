package com.spring.service;

import java.text.ParseException;
import java.util.List;

import com.spring.entity.Student;
import com.spring.model.ResponStudent;
import com.spring.model.StudentDto;

public interface StudentService {
	List<ResponStudent> lstStudentDto();

	StudentDto save(StudentDto student) throws ParseException;
	
	void deleteById(long id);
	
	StudentDto upsert(Student student);
	
	Student findById(long id);
}