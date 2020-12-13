package com.movie.module.employee.dao;

import com.movie.module.employee.model.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: movie
 * @Date: 2020/5/20 19:12
 */
public interface EmployeeMapper {
    @Select("select * from employees limit 5")
    List<Employee> list();
}
