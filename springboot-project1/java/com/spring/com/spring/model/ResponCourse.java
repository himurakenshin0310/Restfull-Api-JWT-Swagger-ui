package com.spring.model;

import java.util.ArrayList;
import java.util.List;

import com.spring.entity.Course;
import com.spring.entity.StudentCourse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponCourse {
	private long id;

	private String name;

	private String description;

	private TeacherDto teacher;

	private List<StudentDto> students;

	public static ResponCourse from(Course course) {
		ResponCourse rs = new ResponCourse();
		rs.setDescription(course.getDescription());
		rs.setId(course.getId());
		rs.setName(course.getName());
		rs.setTeacher(TeacherDto.from(course.getTeacher()));
		List<StudentDto> lst = new ArrayList<StudentDto>();
		for (StudentCourse s : course.getStudentCourse())
			lst.add(StudentDto.from(s.getStudent()));
		rs.setStudents(lst);
		return rs;
	}
}
