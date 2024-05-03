package org.example.demo11.controller;

import org.example.demo11.dto.DTOEmployee;
import org.example.demo11.model.Employee;
import org.example.demo11.service.EmployeeService;
import org.example.demo11.service.IEmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet (name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    private final IEmployeeService iEmployeeService = new EmployeeService();
    private Employee employee = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }switch (action){
            case "show":
                showEmployee(req, resp);
                break;
            case "add":
                showAddForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                try {
                    deleteEmployee(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                listEmployee(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }switch (action){
            case "add":
                addEmployee(req, resp);
                break;
            case "edit":
                editEmployee(req, resp);
                break;
            default:
                resp.sendRedirect("/employee");
                break;
        }

    }

    private void editEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String fullName = req.getParameter("full_name");
        String address = req.getParameter("address");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Double salary = Double.valueOf(req.getParameter("salary"));
        employee = new Employee(id, fullName,address,dob,salary);
        iEmployeeService.updateEmployee(employee);
        resp.sendRedirect("/employee?action=list");
    }

    private void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fullName = req.getParameter("full_name");
        String address = req.getParameter("address");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Double salary = Double.valueOf(req.getParameter("salary"));
        employee = new Employee(fullName,address,dob,salary);
        iEmployeeService.addEmployee(employee);
        resp.sendRedirect("/employee?action=list");
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        iEmployeeService.deleteEmployeeByID(id);
        resp.sendRedirect("/employee?action=list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        employee = iEmployeeService.findEmployeeByID(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee_form.jsp");
        req.setAttribute("employee", employee);
        req.setAttribute("action", "edit");
        requestDispatcher.forward(req,resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/employee_form.jsp");
        req.setAttribute("action","add");
        dispatcher.forward(req,resp);
    }

    private void showEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        employee = iEmployeeService.findEmployeeByID(id);
        req.setAttribute("employee", employee);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/employee_details.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void listEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DTOEmployee> employeeList = iEmployeeService.findAll();
        req.setAttribute("employeeList", employeeList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/employee_list.jsp");
        requestDispatcher.forward(req,resp);
    }
}
