package com.spring.model;

import java.util.List;

import com.spring.entity.Teacher;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponTeacher {
	private long id;

	private String name;

	private String birthDate;

	private List<CourseDto> lstCourse;

	public static ResponTeacher from(Teacher teacher) {
		ResponTeacher rs = new ResponTeacher();
		rs.setId(teacher.getId());
		rs.setName(teacher.getName());
		rs.setBirthDate(teacher.getBrithdate().toString());
		rs.setLstCourse(teacher.getCourses().stream().map(CourseDto::from).toList());
		return rs;
	}
}
