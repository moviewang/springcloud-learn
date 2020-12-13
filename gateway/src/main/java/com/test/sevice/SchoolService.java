package com.test.sevice;

import com.test.rpc.RemoteClass;

/**
 * @Author: movie
 * @Date: 2020/3/27 12:58
 */
@RemoteClass("com.movie.easyrpc.service.SchoolService")
public interface SchoolService {
    String getName(Integer id);
}