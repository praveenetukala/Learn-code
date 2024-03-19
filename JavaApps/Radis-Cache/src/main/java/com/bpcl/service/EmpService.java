package com.bpcl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bpcl.Repo.EmpRepo;
import com.bpcl.dto.Employee;

@Service
public class EmpService {

	@Autowired
	private EmpRepo empRepo;

	public Employee saveEmp(Employee employee) {
		return empRepo.save(employee);
	}

	@CachePut(value = "employee", key = "#empId")
	public Employee updateEmp(Integer empId, Employee employee) {
		Optional<Employee> empl = empRepo.findById(empId);
		if (empl.isEmpty()) {
			return null;
		}
		return empRepo.save(employee);
	}

	@CacheEvict(value = "employee", key = "#empId", allEntries = true)
	public void deleteEmp(Integer empId) {
		empRepo.deleteById(empId);
	}

	@Cacheable(value = "employee", key = "#empId")
	public Optional<Employee> getOneEmp(Integer empId) {
		return empRepo.findById(empId);
	}

	@Cacheable(value = "employee")
	public List<Employee> getAllEmp() {
		return empRepo.findAll();
	}
}
