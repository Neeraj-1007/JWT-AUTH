package com.neoSoft.JWTBasedAuthentication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoSoft.JWTBasedAuthentication.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	public Student findById(long id);
}
