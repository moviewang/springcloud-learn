package com.movie.module.employee.service;

import com.movie.module.employee.dao.EmployeeMapper;
import com.movie.module.employee.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: movie
 * @Date: 2020/5/20 20:13
 */
@Service
public class EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Transactional
    public List<Employee> list() {
        return employeeMapper.list();
    }
}
