package org.tutorials.springbootmongodbcrudsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutorials.springbootmongodbcrudsample.exception.EmployeeNotFoundException;
import org.tutorials.springbootmongodbcrudsample.model.Employee;
import org.tutorials.springbootmongodbcrudsample.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(id);
    }
}
