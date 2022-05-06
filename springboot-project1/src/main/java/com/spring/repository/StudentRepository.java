package com.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Student;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = "select s from Student s join fetch s.studentCourse c where c.course.id = ?1 ")
	Student checkIfCourseHaveStudent(long courseId);
}
