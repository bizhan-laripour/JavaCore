package net.tooka.dao;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public interface QueryFactory<T> {

    public String save(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException;

    public String update(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException;

    public String delete(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException;

    public String findAll(T t);

    public String findById(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException;
}
