package com.neoSoft.JWTBasedAuthentication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoSoft.JWTBasedAuthentication.model.Student;
import com.neoSoft.JWTBasedAuthentication.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	public long save(@RequestBody Student student) {
		long pk = studentService.save(student);
		return pk;
	}

	@GetMapping("/getStudentList")
	public List<Student> getList() {
		List list = new ArrayList<>();
		list = studentService.getList();
		return list;
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		studentService.delete(id);
		return "Successfully Deleted";
	}
}
