package com.spring.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.spring.entity.StudentCourse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentCourseDto {
	@Nullable
	private long id;

	@NotNull
	@Min(value = 1)
	private long studentId;

	@NotNull
	@Min(value = 1)
	private long courseId;

	public static StudentCourseDto from(StudentCourse sc) {
		StudentCourseDto dto = new StudentCourseDto();
		dto.setId(sc.getId());
		dto.setCourseId(sc.getCourse().getId());
		dto.setStudentId(sc.getStudent().getId());
		return dto;
	}

}
