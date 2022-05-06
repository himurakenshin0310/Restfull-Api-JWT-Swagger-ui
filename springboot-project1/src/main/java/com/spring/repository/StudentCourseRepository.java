package com.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.StudentCourse;

@Repository
@Transactional
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

	@Query(value = "select sc from StudentCourse sc join sc.course c join sc.student s where s.id = ?1 and c.id = ?2")
	StudentCourse checkDuplicateStudent(long stuId, long courId);
}
