package com.example.demo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.example.demo.model.Employee;

@Component
public class EmployeeResourceAssembler implements  ResourceAssembler<Employee, Resource<Employee>>{

	
	public Resource<Employee> toResource(Employee employee) {

		return new Resource<>(employee,
			linkTo(methodOn(EmployeeController.class).findone(employee.getId())).withSelfRel(),
			linkTo(methodOn(EmployeeController.class).all()).withRel("employee"));
	}

	
	
}
