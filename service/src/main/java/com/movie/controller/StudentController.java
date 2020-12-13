package com.movie.controller;

import com.movie.module.employee.service.EmployeeService;
import com.movie.module.student.dao.StudentMapper;
import com.movie.module.student.model.Student;
import com.movie.module.student.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: movie
 * @Date: 2020/4/29 17:56
 */
@RestController("student")
public class StudentController {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentService studentService;
    @Resource
    private EmployeeService employeeService;

    @GetMapping
    public List<Student> list() {
        studentService.add();
        System.out.println(employeeService.list());
        return studentMapper.list();
    }
}