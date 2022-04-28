package com.spring;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spring.entity.Student;
import com.spring.model.StudentDto;
import com.spring.repository.StudentRepository;
import com.spring.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Transactional
@Slf4j
public class TestStudent {
	@Autowired
	StudentRepository studentRepo;

	@Autowired
	StudentService studentService;

	@Test
	public void testingInsertAndGetListAndRemoveAndUpdateStudent() throws ParseException {
		Student student = new Student();
		student.setName("testStudent");
		student.setBirthday(new Date(new SimpleDateFormat("dd/MM/yyyy").parse("14/05/2022").getTime()));
		studentService.save(StudentDto.from(student));
		log.info("insert new student");
		log.info("danh sach {}", studentRepo.findAll().size());
		log.info("get list student");
		studentService.deleteById(1l);
		log.info("delete student");
	}

}
