package com.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Course;
import com.spring.entity.Teacher;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query(value = "select c from Course c where c.teacher.id = ?1")
	List<Course> findAllByteacherId(long teacherId);

	@Query(value = "select c.teacher from Course c where c.teacher.id = ?1")
	Teacher checkIfHaveTeacher(long teacherId);
}
