package com.spring.model;

import java.util.ArrayList;
import java.util.List;

import com.spring.entity.Student;
import com.spring.entity.StudentCourse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponStudent {
	private long id;
	
	private String name;
	
	private String birthday;
	
	List<CourseDto> lstCourse;
	
	public static ResponStudent from(Student student) {
		List<CourseDto> courseDto = new ArrayList<CourseDto>();
		ResponStudent s = new ResponStudent();
		s.setId(student.getId());
		s.setName(student.getName());
		s.setBirthday(student.getBirthday().toString());
		System.out.println(s.getBirthday());
		for(StudentCourse stu : student.getStudentCourse())
			courseDto.add(CourseDto.from(stu.getCourse()));
		s.setLstCourse(courseDto);
		return s;
	}
}
