package com.example.employeemanager.controller;


import com.example.employeemanager.model.Employee;
import com.example.employeemanager.service.EmployeeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Get
    public HttpResponse<List<Employee>> getAllEmployees(){
        final List<Employee> employees = employeeService.findAllEmployees();
        return HttpResponse.ok(employees);
    }

    @Get("/{id}")
    public HttpResponse<Employee> getAllEmployeeById(@PathVariable("id") final Long id){
        final Employee employee = employeeService.findEmployeeById(id);
        return HttpResponse.ok(employee);
    }

    @Post
    public HttpResponse<Employee> addEmployee(@Body final Employee employee){
        final Employee newEmployee = employeeService.addEmployee(employee);
        return HttpResponse.created(newEmployee);
    }

    @Put("/{id}")
    public HttpResponse<Employee> updateEmployee(@Body Employee employee, @PathVariable("id") final Long id){
        final Employee foundEmployee = employeeService.findEmployeeById(id);
        foundEmployee.setName(employee.getName());
        foundEmployee.setEmail(employee.getEmail());
        foundEmployee.setJobTitle(employee.getJobTitle());
        foundEmployee.setPhone(employee.getPhone());
        foundEmployee.setImageUrl(employee.getImageUrl());
        final Employee updatedEmployee = employeeService.updateEmployee(foundEmployee);
        return HttpResponse.ok(updatedEmployee);
    }

    @Delete("/{id}")
    public HttpResponse<Employee> deleteEmployee(@PathVariable("id") final Long id){
        employeeService.deleteEmployee(id);
        return HttpResponse.ok();
    }
}
