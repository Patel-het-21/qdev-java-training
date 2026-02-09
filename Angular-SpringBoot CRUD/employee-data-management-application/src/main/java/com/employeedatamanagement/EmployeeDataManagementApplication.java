package com.employeedatamanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for Employee Data Management System.
 * This application provides employee CRUD operations with multi-tenant support.
 */
@SpringBootApplication
public class EmployeeDataManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDataManagementApplication.class, args);
    }
}