package net.tooka.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class QueryFactory<T> {



  public String save(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException {
    StringBuilder queryBuilder = new StringBuilder();
    String entity = t.getClass().getSimpleName();
    queryBuilder.append("INSERT INTO " + entity + " VALUES(");
    Class<?> objectClass = t.getClass();
    for (Field field : objectClass.getDeclaredFields()) {
      // get getter for fields
      Object object =
          new PropertyDescriptor(field.getName(), t.getClass()).getReadMethod().invoke(t);
      if (object != null) {
        if (object instanceof String) {
          queryBuilder.append("'" + object.toString() + "' ,");
        } else {
          queryBuilder.append(object.toString() + " ,");
        }
      }else{
        queryBuilder.append("'null' ,");
      }
    }
    queryBuilder.deleteCharAt(queryBuilder.length()-1);
    queryBuilder.append(")");

    return queryBuilder.toString();
  }
}
