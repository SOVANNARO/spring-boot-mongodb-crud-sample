package org.tutorials.springbootmongodbcrudsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tutorials.springbootmongodbcrudsample.exception.EmployeeNotFoundException;
import org.tutorials.springbootmongodbcrudsample.model.Employee;
import org.tutorials.springbootmongodbcrudsample.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(404).body("Employee with ID " + id + " not found.");
        }
    }

    @PostMapping("create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(createdEmployee);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        try {
            employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok("Employee updated successfully.");
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
