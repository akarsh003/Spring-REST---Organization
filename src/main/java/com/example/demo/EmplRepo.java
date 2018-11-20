package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Employee;

@CrossOrigin()
public interface EmplRepo extends JpaRepository<Employee, Integer> {


}
