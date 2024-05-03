package org.example.demo11.model;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String fullName;
    private String address;
    private LocalDate dob;
    private double salary;

    public Employee() {
    }
    public Employee(int id, String fullName, String address, LocalDate dob, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.dob = dob;
        this.salary = salary;
    }

    public Employee(String fullName, String address, LocalDate dob, Double salary) {
        this.fullName = fullName;
        this.address = address;
        this.dob = dob;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
