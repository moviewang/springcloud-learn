package com.movie.module.student.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * @Author: movie
 * @Date: 2020/5/19 13:59
 */
public interface ScoreMapper {
    @Insert("insert into t_score (stu_id, score) values (#{stuId}, #{score})")
    void insert(int stuId, int score);
}
