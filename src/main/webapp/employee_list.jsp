<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Employee List</h1>
    <table class="table mt-3">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>Address</th>
            <th>Date of Birth</th>
            <th>Salary</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employeeList}" var="employee">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.fullName}</td>
                <td>${employee.address}</td>
                <td>${employee.dob}</td>
                <td>${employee.salary}</td>
                <td>
                    <a class="btn btn-info" href="employee?action=show&id=${employee.id}">View</a>
                    <a class="btn btn-primary" href="employee?action=edit&id=${employee.id}">Edit</a>
                    <a class="btn btn-danger" href="employee?action=delete&id=${employee.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-success" href="employee?action=add">Add New Employee</a>
</div>
</body>
</html>
