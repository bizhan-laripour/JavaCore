package net.tooka.dao;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public interface QueryFactory<T> {

   String save(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException;

   String update(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException;

   String delete(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException;

   String findAll(T t);

   String findById(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException;
}
