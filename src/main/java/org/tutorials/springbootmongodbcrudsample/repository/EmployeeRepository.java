package org.tutorials.springbootmongodbcrudsample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tutorials.springbootmongodbcrudsample.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
