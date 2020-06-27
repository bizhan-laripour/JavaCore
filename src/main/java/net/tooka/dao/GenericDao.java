package net.tooka.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, ID> {

  T save(T t) throws SQLException;

  void update(T t);

  void delete(T t);

  List<T> findAll();

  T findByID(ID id);
}
