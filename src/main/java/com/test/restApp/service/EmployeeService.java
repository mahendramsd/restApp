package com.test.restApp.service;

import com.test.restApp.common.CommonDAO;
import com.test.restApp.dao.EmployeeDao;
import com.test.restApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class EmployeeService extends AbstractSingleService<Employee> {

    @Autowired
    EmployeeDao employeeDao;



    @Override
    public CommonDAO<Employee> getDAO() {
        return employeeDao;
    }


}
