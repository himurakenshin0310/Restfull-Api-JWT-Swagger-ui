package com.spring.serviceimp;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Student;
import com.spring.model.ResponStudent;
import com.spring.model.StudentDto;
import com.spring.repository.StudentRepository;
import com.spring.service.StudentService;

@Service
@Transactional
public class StudentServiceImp implements StudentService {

	@Autowired
	StudentRepository studentRepo;

	@Override
	public List<ResponStudent> lstStudentDto() {
		return studentRepo.findAll().stream().map(ResponStudent::from).toList();
	}

	@Override
	public StudentDto save(StudentDto student) throws ParseException {
		return StudentDto.from(studentRepo.save(StudentDto.from(student)));
	}

	@Override
	public void deleteById(long id) {
		studentRepo.deleteById(id);
	}

	@Override
	public StudentDto upsert(Student student) {
		return StudentDto.from(studentRepo.save(student));
	}

	@Override
	public Student findById(long id) {
		return studentRepo.findById(id).get();
	}

}
