package com.example.employee_app.Exceptions;

public class EmployeeAlreadyExists extends RuntimeException {
    public EmployeeAlreadyExists(String message) {
        super(message);
    }
}
