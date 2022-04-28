package com.spring.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Course;
import com.spring.model.CourseDto;
import com.spring.model.ResponCourse;
import com.spring.repository.CourseRepository;
import com.spring.repository.StudentRepository;
import com.spring.repository.TeacherRepository;
import com.spring.service.CourseService;

@Service
@Transactional
public class CourseServiceImp implements CourseService {

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	TeacherRepository teacherRepo;

	@Autowired
	StudentRepository studentRepo;

	@Override
	public CourseDto save(CourseDto course) {
		Course c = new Course();
		c.setName(course.getName());
		c.setDescription(course.getDescription());
		c.setTeacher(teacherRepo.findById(course.getTeacherId()).get());
		return CourseDto.from(courseRepo.save(c));
	}

	@Override
	public List<ResponCourse> findAll() {
		return courseRepo.findAll().stream().map(ResponCourse::from).toList();
	}

	@Override
	public void deleteById(long id) throws Exception {
//		if (!studentRepo.checkIfCourseHaveStudent(id).getName().equals(""))
//			throw new Exception("cant remove course have student!");
		courseRepo.deleteById(id);
	}

	@Override
	public Course findById(long id) {
		return courseRepo.findById(id).get();
	}

	@Override
	public Course upsert(Course course) {
		return courseRepo.save(course);
	}

}
