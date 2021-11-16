package com.movie.module.student.dao;

import com.movie.module.student.entity.TStudent;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TStudent)表数据库访问层
 *
 * @author makejava
 * @since 2021-10-28 18:16:18
 */
public interface TStudentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TStudent queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TStudent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tStudent 实例对象
     * @return 对象列表
     */
    List<TStudent> queryAll(TStudent tStudent);

    /**
     * 新增数据
     *
     * @param tStudent 实例对象
     * @return 影响行数
     */
    int insert(TStudent tStudent);

    /**
     * 修改数据
     *
     * @param tStudent 实例对象
     * @return 影响行数
     */
    int update(TStudent tStudent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}