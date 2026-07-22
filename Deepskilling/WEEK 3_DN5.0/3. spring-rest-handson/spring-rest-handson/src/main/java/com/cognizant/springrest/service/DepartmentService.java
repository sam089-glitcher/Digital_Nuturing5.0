package com.cognizant.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.springrest.dao.DepartmentDao;
import com.cognizant.springrest.model.Department;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public DepartmentService() {
    }

    @Transactional
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}