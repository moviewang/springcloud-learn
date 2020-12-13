package com.movie.module.student.service.impl;

import com.movie.module.student.entity.TStudent;
import com.movie.module.student.dao.TStudentDao;
import com.movie.module.student.service.TStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TStudent)表服务实现类
 *
 * @author makejava
 * @since 2020-04-29 17:01:54
 */
@Service("tStudentService")
public class TStudentServiceImpl implements TStudentService {
    @Resource
    private TStudentDao tStudentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TStudent queryById(Integer id) {
        return this.tStudentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TStudent> queryAllByLimit(int offset, int limit) {
        return this.tStudentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tStudent 实例对象
     * @return 实例对象
     */
    @Override
    public TStudent insert(TStudent tStudent) {
        this.tStudentDao.insert(tStudent);
        return tStudent;
    }

    /**
     * 修改数据
     *
     * @param tStudent 实例对象
     * @return 实例对象
     */
    @Override
    public TStudent update(TStudent tStudent) {
        this.tStudentDao.update(tStudent);
        return this.queryById(tStudent.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tStudentDao.deleteById(id) > 0;
    }
}