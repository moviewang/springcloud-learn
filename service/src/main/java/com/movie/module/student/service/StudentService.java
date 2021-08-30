package com.movie.module.student.service;

import com.movie.module.student.dao.ScoreMapper;
import com.movie.module.student.dao.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2020/5/19 13:56
 */
@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ScoreMapper scoreMapper;

    @Transactional
    public int add() {
        return studentMapper.insert("王六", 23);
    }

    //    @Transactional()
    public void addScore() {
        new Thread(() -> {
            int id = add();
            scoreMapper.insert(id, 23);
        }).start();
    }
}
