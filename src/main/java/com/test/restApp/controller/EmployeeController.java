package com.test.restApp.controller;

import com.test.restApp.common.Responce;
import com.test.restApp.common.ResponceBuilder;
import com.test.restApp.model.Employee;
import com.test.restApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    ResponceBuilder responceBuilder;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "save",method= RequestMethod.POST)
    public Responce save(Employee employee) {
        try {
            return responceBuilder.getResponce(employeeService.save(employee));
        }catch (Exception e) {
            e.printStackTrace();
            return responceBuilder.getResponce(e.getMessage(), false);
        }

    }

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public Responce getById(@PathVariable("id") Integer id) {
        try {
            return responceBuilder.getResponce(employeeService.get(id));
        }catch (Exception e) {
            return responceBuilder.getResponce(e.getMessage(), false);
        }
    }

    @RequestMapping(value="getAll",method=RequestMethod.GET)
    public Responce getAll() {
        try {
            return responceBuilder.getResponce(employeeService.getAll());
        }catch (Exception e) {
            return responceBuilder.getResponce(e.getMessage(), false);
        }
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public Responce deleteById(@PathVariable("id") Integer id) {
        try {
            return responceBuilder.getResponce(employeeService.delete(id));
        }catch (Exception e) {
            return responceBuilder.getResponce(e.getMessage(), false);
        }
    }



}
