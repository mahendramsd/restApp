package com.test.restApp.dao;

import com.test.restApp.common.CommonDAO;
import com.test.restApp.common.HibernateTemplateImpl;
import com.test.restApp.model.Employee;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class EmployeeDao extends CommonDAO<Employee> {

    @Autowired
    HibernateTemplateImpl hibernateTemplate;

    public EmployeeDao() {
        super(Employee.class);
    }


}
