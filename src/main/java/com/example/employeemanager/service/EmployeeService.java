package com.example.employeemanager.service;

import com.example.employeemanager.exception.UserNotFoundException;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;

@Singleton
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepo;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepo = employeeRepository;
    }

    public Employee addEmployee(final Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(final Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("This userID " + id + " cannot be found"));
    }

    public Employee updateEmployee(final Employee employee) {
        return employeeRepo.update(employee);
    }

    public void deleteEmployee(final Long id) {
        employeeRepo.deleteById(id);
    }
}

