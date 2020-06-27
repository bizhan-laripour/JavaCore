package net.tooka.dao.impl;

import net.tooka.config.DbConfig;
import net.tooka.dao.GenericDao;
import net.tooka.dao.QueryFactory;
import net.tooka.mapper.ResultSetMapper;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {

  private Connection connection = DbConfig.getConnection();

  private QueryFactory<T> queryFactory;

  private ResultSetMapper<T> resultSetMapper;

  public GenericDaoImpl(QueryFactory<T> queryFactory , ResultSetMapper<T> resultSetMapper) {
    this.queryFactory = queryFactory;
    this.resultSetMapper = resultSetMapper;
  }

  @Override
  public void save(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement = connection.createStatement();
    statement.executeUpdate(queryFactory.save(t));
  }

  @Override
  public void update(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement = connection.createStatement();
    statement.executeUpdate(queryFactory.update(t));
  }

  @Override
  public void delete(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement = connection.createStatement();
    statement.executeUpdate(queryFactory.delete(t));
  }

  @Override
  public List<T> findAll(T t) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(queryFactory.findAll(t));
    List<T> list = new ArrayList<>();
    return null;

  }

  @Override
  public T findByID(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(queryFactory.findById(t));
    return null;
  }
}
