package com.cognizant.springrest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springrest.model.Employee;

@Repository
public class EmployeeDao {

    private static List<Employee> EMPLOYEE_LIST;

    @SuppressWarnings("unchecked")
    public EmployeeDao() {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("employee.xml");

        EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList");
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}