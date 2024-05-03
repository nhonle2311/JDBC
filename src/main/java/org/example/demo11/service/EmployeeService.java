package org.example.demo11.service;
import org.example.demo11.dto.DTOEmployee;
import org.example.demo11.model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.example.demo11.config.ConnectionJDBC.getConnection;
public class EmployeeService implements IEmployeeService {
    private final List<DTOEmployee> employeeList = new ArrayList<>();


    @Override
    public List<DTOEmployee> findAll() {
        List<DTOEmployee> dtoEmployeeList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String full_name = resultSet.getString("full_name");
                String address = resultSet.getString("address");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                double salary = resultSet.getDouble("salary");
                DTOEmployee dtoEmployee = new DTOEmployee(id, full_name, address,dob,salary);
                dtoEmployeeList.add(dtoEmployee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dtoEmployeeList;
    }

    @Override
    public Employee findEmployeeByID(int id) {
        Connection connection = getConnection();
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id1 = resultSet.getInt("id");
                String full_name = resultSet.getString("full_name");
                String address = resultSet.getString("address");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                double salary = resultSet.getDouble("salary");
                employee = new Employee(id1, full_name, address,dob,salary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void addEmployee(Employee employee) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee (full_name, address, dob, salary) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, employee.getFullName());
            preparedStatement.setString(2, employee.getAddress());
            preparedStatement.setDate(3, Date.valueOf(employee.getDob()));
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateEmployee(Employee employee) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET full_name=?, address=?, dob=?, salary=? WHERE id=?");
            preparedStatement.setString(1, employee.getFullName());
            preparedStatement.setString(2, employee.getAddress());
            preparedStatement.setDate(3, Date.valueOf(employee.getDob()));
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee with ID " + employee.getId() + " updated successfully.");
            } else {
                System.out.println("Failed to update employee with ID " + employee.getId() + ".");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteEmployeeByID(int id) throws SQLException {
        Connection connection = getConnection();
        try {
            // Thực hiện lệnh SQL DELETE
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            // Kiểm tra xem có cần thiết rollback hay không
            if (rowsAffected > 0) {
                connection.rollback();
                System.out.println("Xóa nhân viên thành công.");
            } else {
                System.out.println("Không có nhân viên nào được xóa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

