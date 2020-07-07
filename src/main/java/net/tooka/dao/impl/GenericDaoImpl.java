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
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {

  protected Connection connection = DbConfig.getConnection();

  protected QueryFactory<T> queryFactory;

  protected ResultSetMapper<T> resultSetMapper;

  public GenericDaoImpl(QueryFactory<T> queryFactory, ResultSetMapper<T> resultSetMapper) {
    this.queryFactory = queryFactory;
    this.resultSetMapper = resultSetMapper;
  }

  @Override
  public void save(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement =
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    statement.executeUpdate(queryFactory.save(t));
  }

  @Override
  public void update(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement =
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    statement.executeUpdate(queryFactory.update(t));
  }

  @Override
  public void delete(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement =
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    statement.executeUpdate(queryFactory.delete(t));
  }

  @Override
  public List<T> findAll(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement =
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    ResultSet resultSet = statement.executeQuery(queryFactory.findAll(t));
    return resultSetMapper.mapResultSetToEntity(resultSet, t);
  }

  @Override
  public T findByID(T t)
      throws SQLException, IllegalAccessException, IntrospectionException,
          InvocationTargetException {
    Statement statement =
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    ResultSet resultSet = statement.executeQuery(queryFactory.findById(t));
    return resultSetMapper.mapResultSetToEntity(resultSet , t).get(0);
  }
}
