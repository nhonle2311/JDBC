<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Employee Details</h1>
    <table class="table mt-3">
        <tbody>
        <tr>
            <td><strong>ID:</strong></td>
            <td>${employee.id}</td>
        </tr>
        <tr>
            <td><strong>Full Name:</strong></td>
            <td>${employee.fullName}</td>
        </tr>
        <tr>
            <td><strong>Address:</strong></td>
            <td>${employee.address}</td>
        </tr>
        <tr>
            <td><strong>Date of Birth:</strong></td>
            <td>${employee.dob}</td>
        </tr>
        <tr>
            <td><strong>Salary:</strong></td>
            <td>${employee.salary}</td>
        </tr>
        </tbody>
    </table>
    <a href="employee?action=list" class="btn btn-secondary">Back to List</a>
</div>
</body>
</html>
