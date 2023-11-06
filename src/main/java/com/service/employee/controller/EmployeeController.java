package com.service.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.employee.model.Employee;
import com.service.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/rest")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;
	@GetMapping("/employees")
	//get all employeers
	public List <Employee> getAll(){
		return repository.findAll();
	}
	
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployee(@PathVariable Integer id) throws Exception{
		Optional<Employee> theEmployee = repository.findById(id);
		
	if(theEmployee.isEmpty())
		 throw new Exception("Employee id not found -"+id);
		
		return theEmployee;
	}
	
	//add mapping for post - add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) 
	{
	repository.save(theEmployee);
	return theEmployee;
}
	//update existing
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) 
	{
	repository.save(theEmployee);
	return theEmployee;
}
	//delete employee
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable Integer id) throws Exception{
		Optional<Employee> theEmployee = repository.findById(id);
		
	if(theEmployee.isEmpty())
		 throw new Exception("Employee id not found -"+id);
		repository.deleteById(id);
		return "Delete employee id - "+id;
	}
	
}