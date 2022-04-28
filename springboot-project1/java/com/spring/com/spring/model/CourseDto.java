package com.spring.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.spring.entity.Course;
import com.spring.validator.NameValid;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseDto {
	@Nullable
	private long id;

	@NotNull
	@NameValid
	private String name;

	@NotNull(message = "description must not be null")
	private String description;

	@NotNull
	@Min(value = 1)
	private long teacherId;

	public static CourseDto from(Course course) {
		CourseDto c = new CourseDto();
		c.setId(course.getId());
		c.setName(course.getName());
		c.setDescription(course.getDescription());
		c.setTeacherId(course.getTeacher().getId());
		return c;
	}
}
