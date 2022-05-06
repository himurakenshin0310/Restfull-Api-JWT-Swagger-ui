package com.spring.serviceimp;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Teacher;
import com.spring.model.ResponTeacher;
import com.spring.model.TeacherDto;
import com.spring.repository.CourseRepository;
import com.spring.repository.TeacherRepository;
import com.spring.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImp implements TeacherService {

	@Autowired
	TeacherRepository teacherRepo;

	@Autowired
	CourseRepository courseRepo;

	public TeacherServiceImp(TeacherRepository repo) {
		this.teacherRepo = repo;
	}

	@Override
	public List<ResponTeacher> lstTeacherDto() {
		return teacherRepo.findAll().stream().map(ResponTeacher::from).collect(Collectors.toList());
	}

	@Override
	public TeacherDto save(TeacherDto teacher) throws ParseException {
		Teacher t = new Teacher();
		t.setName(teacher.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		t.setBrithdate(new Date(sdf.parse(teacher.getBirthDate()).getTime()));
		teacherRepo.save(t);
		return TeacherDto.from(t);
	}

	@Override
	public void deleteById(long id) throws Exception {
		if (courseRepo.checkIfHaveTeacher(id) != null)
			throw new Exception("The teacher have course so cant remove!");
		teacherRepo.deleteById(id);

	}

	@Override
	public TeacherDto upsert(Teacher teacher) {
		return TeacherDto.from(teacherRepo.save(teacher));
	}

	@Override
	public Teacher findById(long id) {
		return teacherRepo.findById(id).get();
	}

}
