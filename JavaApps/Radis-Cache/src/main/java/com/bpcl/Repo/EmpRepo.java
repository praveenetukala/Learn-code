package com.bpcl.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpcl.dto.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer> {

}
