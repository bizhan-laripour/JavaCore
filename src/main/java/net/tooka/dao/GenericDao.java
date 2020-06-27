package net.tooka.dao;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

  void save(T t) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException;

  void update(T t) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException;

  void delete(T t) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException;

  List<T> findAll(T t) throws SQLException;

  T findByID(T t) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException;
}
