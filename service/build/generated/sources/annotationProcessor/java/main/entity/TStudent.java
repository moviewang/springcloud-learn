package com.movie.module.student.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TStudent)实体类
 *
 * @author makejava
 * @since 2020-04-29 16:51:42
 */
public class TStudent implements Serializable {
    private static final long serialVersionUID = 791818101740711969L;
    /**
    * 自增id
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 年龄
    */
    private In age;
    /**
    * 创建时间
    */
    private Date ctime;
    /**
    * 更新时间
    */
    private Date mtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public In getAge() {
        return age;
    }

    public void setAge(In age) {
        this.age = age;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

}