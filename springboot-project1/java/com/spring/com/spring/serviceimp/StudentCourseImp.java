package com.spring.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.StudentCourse;
import com.spring.model.StudentCourseDto;
import com.spring.repository.CourseRepository;
import com.spring.repository.StudentCourseRepository;
import com.spring.repository.StudentRepository;
import com.spring.service.CourseService;
import com.spring.service.StudentCourseService;
import com.spring.service.StudentService;

@Service
public class StudentCourseImp implements StudentCourseService {

	@Autowired
	StudentCourseRepository studentCourseRepo;

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	CourseRepository courseRepo;

	@Override
	public StudentCourse save(StudentCourseDto dto) throws Exception {
		if(checkDuplicateData(dto.getStudentId(), dto.getCourseId()))
			throw new Exception("Student already have in course!");
		StudentCourse s = new StudentCourse();
		s.setCourse(courseRepo.findById(dto.getCourseId()).get());
		s.setStudent(studentRepo.findById(dto.getStudentId()).get());
		return studentCourseRepo.save(s);
	}

	private boolean checkDuplicateData(long stuId, long courId) {
		return studentCourseRepo.checkDuplicateStudent(stuId, courId) != null;
	}

	@Override
	public List<StudentCourseDto> findAll() {
		return studentCourseRepo.findAll().stream().map(StudentCourseDto::from).toList();
	}

	@Autowired
	CourseService courseService;

	@Autowired
	StudentService studentService;

	@Override
	public StudentCourse findById(long id) {
		return studentCourseRepo.findById(id).get();
	}

	@Override
	public void update(StudentCourseDto dto) {
		StudentCourse sc = studentCourseRepo.findById(dto.getId()).get();
		sc.setCourse(courseService.findById(dto.getCourseId()));
		sc.setStudent(studentService.findById(dto.getStudentId()));
		studentCourseRepo.save(sc);
	}

}
