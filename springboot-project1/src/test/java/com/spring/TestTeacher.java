package com.spring;

import javax.transaction.Transactional;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Transactional
@Slf4j
public class TestTeacher {

//	@Autowired
//	TeacherService teacherService;
//
//	@Autowired
//	TeacherRepository teacherRepo;
//
//	@Test
//	public void testingInsertAndGetListAndRemoveAndUpsertTeacher() throws Exception {
//		Teacher teacher = new Teacher();
//		teacher.setBrithdate(new Date(new SimpleDateFormat("dd/MM/yyyy").parse("14/05/2022").getTime()));
//		teacher.setName("teacher test");
//		teacherService.save(TeacherDto.from(teacher));
//		log.info("insert new teacher");
//		for (Teacher t : teacherRepo.findAll())
//			log.info("test teacher: teacher exist now have id : {}", t.getId());
////		log.info("get list of teacher {}", teacherRepo.findAll().size());		teacherService.upsert(teacherService.findById(1l));
//		log.info("update teacher");
//		teacherService.deleteById(1l);
//		log.info("remove teacher");
//	}
}
