package org.tutorials.springbootmongodbcrudsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found."));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void updateEmployee(String id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee with ID " + id + " not found.");
        }
    }
}
