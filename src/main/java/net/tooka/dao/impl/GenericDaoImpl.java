package net.tooka.dao.impl;

import net.tooka.config.DbConfig;
import net.tooka.dao.GenericDao;
import net.tooka.dao.QueryFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GenericDaoImpl<T, ID>  implements GenericDao<T, ID> {

  private Connection connection = DbConfig.getConnection();



  @Override
  public T save(T t) throws SQLException {
      Statement statement = connection.createStatement();
      return null;

  }

  @Override
  public void update(T t)  {}

  @Override
  public void delete(T t) {}

  @Override
  public List<T> findAll() {
    return null;
  }

  @Override
  public T findByID(ID id) {
    return null;
  }

}
