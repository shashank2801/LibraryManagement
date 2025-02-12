package com.shashank.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shashank.LMS.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
