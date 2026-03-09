package com.example.employee_app.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_app.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
    public List<Employee> findByName(String name);
}
