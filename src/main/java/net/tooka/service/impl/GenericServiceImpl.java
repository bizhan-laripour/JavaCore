package net.tooka.service.impl;

import net.tooka.dao.GenericDao;
import net.tooka.service.GenericService;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GenericServiceImpl<T> implements GenericService<T> {

  protected GenericDao<T> genericDao;

  public GenericServiceImpl(GenericDao<T> genericDao) {
    this.genericDao = genericDao;
  }

  @Override
  public void save(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException {
    genericDao.save(t);
  }

  @Override
  public void update(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException {
    genericDao.update(t);
  }

  @Override
  public void delete(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException {
    genericDao.delete(t);
  }

  @Override
  public List<T> findAll(T t)
      throws InvocationTargetException, SQLException, IntrospectionException,
          IllegalAccessException {
    return genericDao.findAll(t);
  }

  @Override
  public T findById(T t) {
    return findById(t);
  }
}
