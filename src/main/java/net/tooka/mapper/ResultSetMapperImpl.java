package net.tooka.mapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * this class map the resultSet to an entity
 * but something wrong is in the mapResultSetToEntity method that the result contains same value for entities
 * now i am working on it
 * @param <T>
 */
public class ResultSetMapperImpl<T> implements ResultSetMapper<T> {

  @Override
  public List<T> mapResultSetToEntity(ResultSet resultSet, T t)
      throws IntrospectionException, SQLException, InvocationTargetException,
          IllegalAccessException {

    Field[] fields = t.getClass().getDeclaredFields();
    List<T> result = new ArrayList<>();
    while (resultSet.next()) {

      Object object = null;
      for (int j = 0; j < fields.length; j++) {

        if (fields[j].getType().getSimpleName().equalsIgnoreCase("long")) {
          object = resultSet.getLong(j + 1);
        } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("int")) {
          object = resultSet.getInt(j + 1);
        } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("Date")) {
          object = resultSet.getDate(j + 1);
        } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("String")) {
          object = resultSet.getString(j + 1);
        } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("timestamp")) {
          object = resultSet.getTimestamp(j + 1);
        } else if (fields[j].getType().getSimpleName().equalsIgnoreCase("time")) {
          object = resultSet.getTime(j + 1);
        }
        new PropertyDescriptor(fields[j].getName(), t.getClass())
            .getWriteMethod()
            .invoke(t, object);
      }
      result.add(t);
    }

    return result;
  }
}
