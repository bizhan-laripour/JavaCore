package net.tooka.dao.impl;

import net.tooka.dao.QueryFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class QueryFactoryImpl<T> implements QueryFactory<T> {

  @Override
  public String save(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException {
    StringBuilder queryBuilder = new StringBuilder();
    String entity = t.getClass().getSimpleName().toUpperCase();
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
      } else {
        queryBuilder.append("'null' ,");
      }
    }
    queryBuilder.deleteCharAt(queryBuilder.length() - 1);
    queryBuilder.append(")");

    return queryBuilder.toString();
  }

  @Override
  public String update(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException {
    StringBuilder queryBuilder = new StringBuilder();
    long id = 0;
    String entity = t.getClass().getSimpleName().toUpperCase();
    queryBuilder.append("UPDATE " + entity + " " + "SET ");
    Class<?> objectClass = t.getClass();
    for (Field field : objectClass.getDeclaredFields()) {
      Object object =
          new PropertyDescriptor(field.getName(), t.getClass()).getReadMethod().invoke(t);
      if (!field.getName().equals("id")) {

        if (object != null) {
          if (object instanceof String) {
            queryBuilder.append(field.getName() + " ='" + object.toString() + "',");
          } else {
            queryBuilder.append(field.getName() + " =" + object.toString() + ",");
          }
        }
      } else {
        id = Long.parseLong(object.toString());
      }
    }
    queryBuilder.delete(queryBuilder.length() - 1, queryBuilder.length());
    queryBuilder.append(" WHERE id=").append(id);
    return queryBuilder.toString();
  }

  @Override
  public String delete(T t)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException {
    StringBuilder queryBuilder = new StringBuilder();
    String entity = t.getClass().getSimpleName().toUpperCase();
    Class<?> objectClass = t.getClass();
    for (Field field : objectClass.getDeclaredFields()) {
      if (field.getName().equals("id")) {
        Object object =
            new PropertyDescriptor(field.getName(), t.getClass()).getReadMethod().invoke(t);
        queryBuilder
            .append("DELETE FROM ")
            .append(entity.toUpperCase())
            .append(" WHERE id=")
            .append(object.toString());
      }
    }
    return queryBuilder.toString();
  }

  @Override
  public String findAll(T t) {
    return "SELECT * FROM " + t.getClass().getSimpleName().toUpperCase();
  }

  @Override
  public String findById(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
    StringBuilder queryBuilder = new StringBuilder();
    String entity = t.getClass().getSimpleName().toUpperCase();
    Class<?> objectClass = t.getClass();
    long id = 0;
    for(Field field : objectClass.getDeclaredFields()){
      if(field.getName().equals("id")){
        Object object =
                new PropertyDescriptor(field.getName(), t.getClass()).getReadMethod().invoke(t);
        id = Long.parseLong(object.toString());
      }
    }
    queryBuilder.append("SELECT * FROM ").append(entity.toUpperCase()).append(" WHERE id=").append(id);
    return queryBuilder.toString();
  }
}
