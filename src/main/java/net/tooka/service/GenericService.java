package net.tooka.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface GenericService<T> {

  public void save(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException;

  public void update(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException;

  public void delete(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException;

  public List<T> findAll(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException;

  public T findById(T t);
}
