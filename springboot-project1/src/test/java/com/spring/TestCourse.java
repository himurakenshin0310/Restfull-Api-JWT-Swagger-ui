package com.spring;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spring.entity.Course;
import com.spring.entity.Teacher;
import com.spring.model.CourseDto;
import com.spring.model.TeacherDto;
import com.spring.repository.CourseRepository;
import com.spring.repository.TeacherRepository;
import com.spring.service.CourseService;
import com.spring.service.TeacherService;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Transactional
@Slf4j
public class TestCourse {
	@Autowired
	CourseService courseService;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	TeacherService teacherService;

	@Autowired
	TeacherRepository teacherRepo;

	@Test
	public void testingInsertAndGetListAndRemoveAndUpdateCourse() throws Exception {
		Teacher teacher = new Teacher();
		teacher.setBrithdate(new Date(new SimpleDateFormat("dd/MM/yyyy").parse("14/05/2022").getTime()));
		teacher.setName("teacher test");
		teacherService.save(TeacherDto.from(teacher));
		log.info("insert new teacher");
		Course c = new Course();
		c.setDescription("mota");
		c.setName("subject 1");
		for (Teacher t : teacherRepo.findAll())
			log.info("test course :teacher exist now have id : {}", t.getId());
		c.setTeacher(teacherService.findById(1l));
		courseService.save(CourseDto.from(c));
		log.info("insert course");
		log.info("get list course {}", courseRepo.findAll().size());
		courseService.upsert(courseService.findById(1l));
		log.info("uppdate course");
		courseService.deleteById(1l);
		log.info("remove course");

	}
}
