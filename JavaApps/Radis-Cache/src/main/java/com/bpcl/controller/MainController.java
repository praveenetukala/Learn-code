package com.bpcl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpcl.dto.Employee;
import com.bpcl.service.EmpService;

@RestController
public class MainController {

	@Autowired
	private EmpService empService;

	@RequestMapping("/save")
	public Employee saveEmp(@RequestBody Employee employee) {
		return empService.saveEmp(employee);
	}

	@RequestMapping("/all")
	public List<Employee> getAll() {
		return empService.getAllEmp();
	}

	@RequestMapping("/update")
	public Employee updateEmp(@RequestBody Employee employee) {
		return empService.updateEmp(employee.getEmpId(), employee);
	}

	@RequestMapping("/getone")
	public Employee getOneEmp(@RequestParam Integer empId) {
		Optional<Employee> employee = empService.getOneEmp(empId);
		if(employee.isEmpty()) {
		return null;	
		}
		return employee.get();
	}

	@RequestMapping("/delete")
	public void delete(@RequestParam Integer empId) {
		empService.deleteEmp(empId);
	}
}
