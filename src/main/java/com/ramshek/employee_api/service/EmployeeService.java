package com.ramshek.employee_api.service;

import com.ramshek.employee_api.entity.Employee;
import com.ramshek.employee_api.exception.EmployeeNotFoundException;
import com.ramshek.employee_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Create Employee
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    //get all Employee
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    //get employee by id
    public Employee getEmployeeById(Long id){
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }else throw new EmployeeNotFoundException(id);
    }

    //update employee details
    public Employee updateEmployee(Long id, Employee employeeDetails){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        return employeeRepository.save(employee);
    }

    //delete employee
    public void deleteEmployee(Long id){
        if(employeeRepository.findById(id).isPresent()) {
             employeeRepository.deleteById(id);
        }else throw new EmployeeNotFoundException(id);
    }


}
