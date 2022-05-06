package com.spring;

import javax.transaction.Transactional;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Transactional
@Slf4j
public class TestStudent {
//	@Autowired
//	StudentRepository studentRepo;
//
//	@Autowired
//	StudentService studentService;

//	@Test
//	public void testingInsertAndGetListAndRemoveAndUpdateStudent() throws ParseException {
//		Student student = new Student();
//		student.setName("testStudent");
//		student.setBirthday(new Date(new SimpleDateFormat("dd/MM/yyyy").parse("14/05/2022").getTime()));
//		studentService.save(StudentDto.from(student));
//		log.info("insert new student");
//		log.info("danh sach {}", studentRepo.findAll().size());
//		log.info("get list student");
//		studentService.deleteById(1l);
//		log.info("delete student");
//	}

}
