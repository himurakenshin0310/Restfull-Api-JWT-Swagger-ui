package com.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Teacher;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
