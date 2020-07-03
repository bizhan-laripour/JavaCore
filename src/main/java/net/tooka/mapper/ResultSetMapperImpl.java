package net.tooka.mapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapperImpl<T> implements ResultSetMapper<T> {

  @Override
  public T mapResultSetToEntity(ResultSet resultSet, T t)
      throws IntrospectionException, SQLException, InvocationTargetException,
          IllegalAccessException {

    Field[] fields = t.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      Object object = null;
      while (resultSet.next()) {
        if (fields[i].getType().getTypeName().equalsIgnoreCase("long")) {
          object = resultSet.getLong(i);
        } else if (fields[i].getType().getTypeName().equalsIgnoreCase("int")) {
          object = resultSet.getInt(i);
        } else if (fields[i].getType().getTypeName().equalsIgnoreCase("Date")) {
          object = resultSet.getDate(i);
        } else if (fields[i].getType().getTypeName().equalsIgnoreCase("varchar")) {
          object = resultSet.getString(i);
        } else if (fields[i].getType().getTypeName().equalsIgnoreCase("timestamp")) {
          object = resultSet.getTimestamp(i);
        } else if (fields[i].getType().getTypeName().equalsIgnoreCase("time")) {
          object = resultSet.getTime(i);
        }
      }
      new PropertyDescriptor(fields[i].getName(), t.getClass()).getWriteMethod().invoke(t, object);
    }
    return t;
  }
}
