package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.example.demo.model.Employee;

@RestController
class EmployeeController {

	private final DeptRepo deptrepo; 
	private final EmplRepo repository;
	private final EmployeeResourceAssembler assembler;
	
	
	EmployeeController(EmplRepo repository,EmployeeResourceAssembler assembler,DeptRepo deptrepo) {
		this.repository = repository;
		this.assembler=assembler;
		this.deptrepo=deptrepo;
	}

	// Aggregate root

	@GetMapping("/employee")
	Resources<Resource<Employee>> all() {

		List<Resource<Employee>> employees = repository.findAll().stream().map(assembler::toResource)
				.collect(Collectors.toList());

		return new Resources<>(employees,
			linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	

//	@PostMapping("/employee")
	@PostMapping("/employee")
	ResponseEntity<Resource<Employee>> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {

		Resource<Employee> resource = assembler.toResource(repository.save(newEmployee));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	

	
	// Single item

	@GetMapping("/employee/{id}")
	Resource<Employee> findone(@PathVariable int id) {

		Employee employee = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException());

		return assembler.toResource(employee);
	}
	

	@PutMapping("/employee/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable int id) {

		return repository.findById(id)
			.map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setGrade(newEmployee.getGrade());
				employee.setSalary(newEmployee.getSalary());
				employee.setDeptid(newEmployee.getDeptid());
				employee.setSkill(newEmployee.getSkill());
				employee.setDoj(newEmployee.getDoj());
				employee.setDesg(newEmployee.getDesg());
				employee.setCity(newEmployee.getCity());
				employee.setCountry(newEmployee.getCountry());
				
				return repository.save(employee);
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return repository.save(newEmployee);
			});
	}

	
	
	@DeleteMapping("/employee/{id}")
	void deleteEmployee(@PathVariable int id) {
		repository.deleteById(id);
	}
}
