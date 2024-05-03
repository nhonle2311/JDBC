package org.example.demo11.service;

import org.example.demo11.dto.DTOEmployee;
import org.example.demo11.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {
    List<DTOEmployee> findAll();

    Employee findEmployeeByID(int id);

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeByID (int id) throws SQLException;
}
