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

import com.example.demo.model.Department;

@RestController
class DepartmentController {

	private final DeptRepo repository;
	private final DepartmentResourceAssembler assembler;
	
	
	DepartmentController(DeptRepo repository,DepartmentResourceAssembler assembler) {
		this.repository = repository;
		this.assembler=assembler;
	}

	// Aggregate root

	@GetMapping("/department")
	Resources<Resource<Department>> all() {

		List<Resource<Department>> Departments = repository.findAll().stream().map(assembler::toResource)
				.collect(Collectors.toList());

		return new Resources<>(Departments,
			linkTo(methodOn(DepartmentController.class).all()).withSelfRel());
	}
	

//	@PostMapping("/Department")
	@PostMapping("/department")
	ResponseEntity<Resource<Department>> newDepartment(@RequestBody Department newDepartment) throws URISyntaxException {

		Resource<Department> resource = assembler.toResource(repository.save(newDepartment));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	

	
	// Single item

	@GetMapping("/department/{id}")
	Resource<Department> findone(@PathVariable int id) {

		Department Department = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException());

		return assembler.toResource(Department);
	}
	
	
	

	@PutMapping("/department/{id}")
	Department replaceDepartment(@RequestBody Department newDepartment, @PathVariable int id) {

		return repository.findById(id)
			.map(Department -> {
				Department.setDeptid(newDepartment.getDeptid());
				Department.setDeptname(newDepartment.getDeptname());
				Department.setDepartmenthead(newDepartment.getDepartmenthead());
				
				return repository.save(Department);
			})
			.orElseGet(() -> {
				newDepartment.setDeptid(id);
				return repository.save(newDepartment);
			});
	}

	
	
	@DeleteMapping("/department/{id}")
	void deleteDepartment(@PathVariable int id) {
		repository.deleteById(id);
	}
}
