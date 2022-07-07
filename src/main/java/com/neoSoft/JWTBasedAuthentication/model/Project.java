package com.neoSoft.JWTBasedAuthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="project")
public class Project {

		@Id
		private Long projid;
		
		@Column(name = "projectName",length = 100)
		private String projname;
		
		@Column(name = "duration",length = 100)
		private String duration;
//		
		@OneToMany
	    private Set<Student> student;
	
		

		public Set<Student> getStudent() {
			return student;
		}

		public void setStudent(Set<Student> student) {
			this.student = student;
		}

		public Project() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Project(Long projid, String projname, String duration,Student student) {
			super();
			this.projid = projid;
			this.projname = projname;
			this.duration = duration;
			this.student= (Set<Student>) student;
		}

		public Long getProjid() {
			return projid;
		}

		public void setProjid(Long projid) {
			this.projid = projid;
		}

		public String getProjname() {
			return projname;
		}

		public void setProjname(String projname) {
			this.projname = projname;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}
		
}
