package com.example.employee_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.employee_app.Exceptions.EmployeeAlreadyExists;
import com.example.employee_app.Exceptions.EmployeeNotFound;
import com.example.employee_app.models.Employee;
import com.example.employee_app.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    // create employee
    public Employee createEmployee(Employee employee) {
        //return employeeRepository.save(employee);

        Optional<Employee> e = employeeRepository.findById(employee.getId());
        if (e.isPresent()) {
            throw new EmployeeAlreadyExists("Student with ID " + employee.getId() + " is already present");
        } else {
            return employeeRepository.save(employee);
        }
    }

    // get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Page<Employee> getAllEmployees(Pageable pageable){
        Page<Employee> emps = employeeRepository.findAll(pageable);
       
        return emps ;
    }

    // get employee by id
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    public Employee getStudentById(Long id) {
        Optional<Employee> s = employeeRepository.findById(id);
        if (s.isPresent()) {
            return s.get();
        } else {
            throw new EmployeeNotFound("Student with ID " + id + " not found");
        }
    }

    // update employee
    public Employee updateEmployee(Long id,Employee employee) {
        Employee e = employeeRepository.findById(id).orElse(null);
        if(e!=null){
            e.setName(employee.getName());
            e.setEmail(employee.getEmail());
            e.setDepartment(employee.getDepartment());
            e.setSalary(employee.getSalary());
            e.setDesignation(employee.getDesignation());
            employeeRepository.save(e);
        }
        return e;
    }

    // delete employee
    public void deleteEmployee(Long id) {
        Optional<Employee> s = employeeRepository.findById(id);
            if (s.isPresent()) {
                employeeRepository.deleteById(id);
            } else {
                throw new EmployeeNotFound("Employee with ID " + id + " not found");
            } 
    }

}
