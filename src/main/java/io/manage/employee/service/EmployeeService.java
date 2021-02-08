package io.manage.employee.service;

import io.manage.employee.entity.Employee;
import io.manage.employee.exception.EmployeeNotFoundException;
import io.manage.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return  employeeRepository.save(employee);
    }

    public Employee findEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id '%s' was not found.", id)));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
