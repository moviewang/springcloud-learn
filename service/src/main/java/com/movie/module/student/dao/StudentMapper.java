package com.movie.module.student.dao;

import com.movie.module.student.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: movie
 * @Date: 2020/4/29 17:09
 */
public interface StudentMapper {
    @Select("select * from t_student")
    List<Student> list();

    @Insert("insert into t_student (name, age) values (#{name}, #{age})")
    @Result(id = true)
    int insert(String name, int age);
}
