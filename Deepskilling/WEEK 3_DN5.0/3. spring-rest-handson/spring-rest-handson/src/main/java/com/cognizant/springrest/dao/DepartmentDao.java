package com.cognizant.springrest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springrest.model.Department;

@Repository
public class DepartmentDao {

    private static List<Department> DEPARTMENT_LIST;

    @SuppressWarnings("unchecked")
    public DepartmentDao() {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("employee.xml");

        DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("departmentList");
    }

    public List<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}