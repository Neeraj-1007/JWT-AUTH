package com.neoSoft.JWTBasedAuthentication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoSoft.JWTBasedAuthentication.Repository.ProjectRepository;
import com.neoSoft.JWTBasedAuthentication.Repository.StudentRepository;
import com.neoSoft.JWTBasedAuthentication.model.Project;
import com.neoSoft.JWTBasedAuthentication.model.Student;

@Service
public class ProjectService {

	
	@Autowired
	private ProjectRepository repository;
	
	@Transactional
	public long save(Project project) {
	List list=repository.findAll();
	for(int i=0;i<list.size();i++) {
		
			list.get(0);
			System.out.println(list.get(1));
		
	}
	Project  proj=	repository.save(project);
	return proj.getProjid();
	}
	
	@Transactional
	public void delete(long id) {
		Project project=repository.findByProjid(id);
		repository.delete(project);
	}
	
	@Transactional
	public List<Project> getList(){
		List list=new ArrayList<>();
		list=repository.findAll();
		return list;
	}
	
}
