package com.spring.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Course;
import com.spring.entity.Student;
import com.spring.entity.Teacher;
import com.spring.model.CourseDto;
import com.spring.model.StudentCourseDto;
import com.spring.model.StudentDto;
import com.spring.model.TeacherDto;
import com.spring.service.CourseService;
import com.spring.service.StudentCourseService;
import com.spring.service.StudentService;
import com.spring.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@CrossOrigin
@Transactional
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/admin/")
public class RestControllers {

	@Autowired
	TeacherService teacherService;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@Autowired
	StudentCourseService studentCourseService;

	@Operation(description = "get list of teacher")
	@GetMapping("list-teacher.html")
	public ResponseEntity<responObject> getListOfTeacher() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "get data succsess!", teacherService.lstTeacherDto()));
	}

	@Operation(description = "get list of student")
	@GetMapping("list-student.html")
	public ResponseEntity<responObject> getListStudent() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "get data succsess!", studentService.lstStudentDto()));
	}

	@Operation(description = "No need id and Name cant be null and dont have numbers , birthdate have foramt dd/MM/yyyy")
	@PostMapping("add-new-teacher.html")
	public ResponseEntity<responObject> postNewTeacher(@Valid @RequestBody TeacherDto teacher) throws ParseException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "Insert new teacher succsess!", teacherService.save(teacher)));
	}

	@Operation(description = "No need id and Name cant be null and dont have numbers , birthday have foramt dd/MM/yyyy")
	@PostMapping("add-new-student.html")
	public ResponseEntity<responObject> postNewStudent(@Valid @RequestBody StudentDto student) throws ParseException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "insert new student succsess!", studentService.save(student)));
	}

	@Operation(description = "No need id and Name cant be null and dont have numbers , birthdate have foramt dd/MM/yyyy")
	@PostMapping("add-new-course.html")
	public ResponseEntity<responObject> postNewCourse(@Valid @RequestBody CourseDto course) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "succsess!", courseService.save(course)));
	}

	@Operation(description = "used for add student into course and no need id")
	@PostMapping("add-new-course-student.html")
	public ResponseEntity<responObject> postNewCourseStudent(@Valid @RequestBody StudentCourseDto studentCourse)
			throws Exception {
		studentCourseService.save(studentCourse);
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "succsess!", null));
	}

	@Operation(description = "get list of course")
	@GetMapping("list-of-course.html")
	public ResponseEntity<responObject> getListOfCourse() {
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "succsess!", courseService.findAll()));
	}

	@Operation(description = "remove teacher from id")
	@DeleteMapping("delete-teacher/{id}")
	public ResponseEntity<responObject> deleteTeacher(@PathVariable long id) throws Exception {
		teacherService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "delete succsess teacher have id: " + id, null));
	}

	@Operation(description = "remove course from id")
	@DeleteMapping("delete-course/{id}")
	public ResponseEntity<responObject> deleteCourse(@PathVariable long id) throws Exception {
		courseService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "delete succsess course have id:" + id, null));
	}

	@Operation(description = "remove student from id")
	@DeleteMapping("delete-student/{id}")
	public ResponseEntity<responObject> deleteStudent(@PathVariable long id) {
		studentService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new responObject("200", "delete succsess student have id:" + id, null));
	}

	@Operation(description = "update teacher and id in request can null, if find by id update else create new ")
	@PutMapping("upsert-teacher.html/{id}")
	public ResponseEntity<responObject> upsertTeacher(@PathVariable long id, @Valid @RequestBody TeacherDto dto)
			throws ParseException {
		Teacher t = teacherService.findById(id);
		t.setBrithdate(new Date(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getBirthDate()).getTime()));
		t.setName(dto.getName());
		teacherService.upsert(t);
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "upsert teacher succsess!", null));
	}

	@Operation(description = "update student and id in request can null, if find by id update else create new ")
	@PutMapping("upsert-student.html/{id}")
	public ResponseEntity<responObject> upsertStudent(@PathVariable long id, @Valid @RequestBody StudentDto student)
			throws ParseException {
		Student s = studentService.findById(id);
		s.setBirthday(new Date(new SimpleDateFormat("dd/MM/yyyy").parse(student.getBirthday()).getTime()));
		s.setName(student.getName());
		studentService.upsert(s);
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "upsert student succsess", null));
	}

	@Operation(description = "update course and id in request can null, if find by id update else create new ")
	@PutMapping("upsert-course.html/{id}")
	public ResponseEntity<responObject> upsertCourse(@PathVariable long id, @Valid @RequestBody CourseDto courseDto) {
		Course c = courseService.findById(id);
		c.setDescription(courseDto.getDescription());
		c.setName(courseDto.getName());
		c.setTeacher(teacherService.findById(courseDto.getTeacherId()));
		courseService.upsert(c);
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "upser succsess!", null));
	}

	@Operation(description = "update studentCourse, id in request can null, if find by id update else create new ")
	@PutMapping("upser-studentcourse.html/{id}")
	public ResponseEntity<responObject> upsertStudentCourse(@PathVariable long id,
			@Valid @RequestBody StudentCourseDto dto) {
		dto.setId(id);
		studentCourseService.update(dto);
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "succsess", null));
	}

	@Operation(description = "get list of studentCourse")
	@GetMapping("list-studentcourse.html")
	public ResponseEntity<responObject> getListStudentCourse() {
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "OK", studentCourseService.findAll()));
	}

}

@Setter
@Getter
@AllArgsConstructor
class responObject {
	String status;
	String msg;
	Object data;
}
