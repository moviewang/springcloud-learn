package com.movie.easyrpc.rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: movie
 * @Date: 2020/3/27 12:32
 */
public class ServiceGetter {
    private static Map<Class, Object> serviceMap = new HashMap<>();

    public static <T> T getServiceByClass(Class<T> clazz) {
        try {
            if (serviceMap.containsKey(clazz)) {
                return (T) serviceMap.get(clazz);
            } else {
                T t = clazz.newInstance();
                serviceMap.put(clazz, t);
                return t;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
