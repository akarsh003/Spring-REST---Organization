package com.example.demo.model;

import java.util.HashSet;
//import lombok.Data;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

//@Data
@Entity
public class Department {

	 
//	 
//	 @OneToMany(mappedBy = "dept")
//	 
	 @Id
	 @GeneratedValue
	 
	 private int deptid;
	 private String deptname;
//	 private String departmenthead;
	 
	 @OneToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name="id",nullable=true)
	 private Employee departmenthead;



	//	 @Id
//	 @OneToMany(mappedBy = "dept")
//	 @OrderBy(value="length")
//	 private Set<Employee> departmentname = new HashSet<>();

	
	 
	 
	 
	 
//	 public void setDepartmenthead(String departmenthead) {
//		this.departmenthead = departmenthead;
//	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	
	public Employee getDepartmenthead() {
		return departmenthead;
	}

	public void setDepartmenthead(Employee departmenthead) {
		this.departmenthead = departmenthead;
	}

//	public String getDepartmenthead() {
//		return departmenthead;
//	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
}
