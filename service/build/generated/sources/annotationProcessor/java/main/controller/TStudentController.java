package com.movie.module.student.controller;

import com.movie.module.student.entity.TStudent;
import com.movie.module.student.service.TStudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TStudent)表控制层
 *
 * @author makejava
 * @since 2020-04-29 16:51:48
 */
@RestController
@RequestMapping("tStudent")
public class TStudentController {
    /**
     * 服务对象
     */
    @Resource
    private TStudentService tStudentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TStudent selectOne(Integer id) {
        return this.tStudentService.queryById(id);
    }

}