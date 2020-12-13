package com.movie.module.student.service;

import com.movie.module.student.entity.TStudent;
import java.util.List;

/**
 * (TStudent)表服务接口
 *
 * @author makejava
 * @since 2020-04-29 17:01:54
 */
public interface TStudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TStudent queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TStudent> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tStudent 实例对象
     * @return 实例对象
     */
    TStudent insert(TStudent tStudent);

    /**
     * 修改数据
     *
     * @param tStudent 实例对象
     * @return 实例对象
     */
    TStudent update(TStudent tStudent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}